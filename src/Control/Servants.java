/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Communicate.FtpClient.FtpClient;
import FileTool.FileService;
import FileTool.MD5;
import FileTool.Zip;
import MOdel.HandlerManager;
import MOdel.ProgramParameter;
import MOdel.Socket.ServerSender;
import MOdel.Source.Setting;
import MOdel.Sql.SqlExecute;
import Unicast.Server.ClientHandler;
import Unicast.commons.Actions.MapRowsParameter;
import Unicast.commons.Actions.Object.MyName;
import Unicast.commons.Actions.TableListRowParameter;
import Unicast.commons.Actions.simplePackage;
import Unicast.commons.Enum.ACTION;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class Servants {

    private static volatile Servants instance;
    private final Setting setting;
    private final SqlExecute sqlExecute;
    private final FileService fileService;
    private HandlerManager handlerManager;
    private final MD5 md5;
    private final Zip zip;
    private final FtpClient ftpClient;

    public static Servants getInstance() {
        Servants ins = Servants.instance;
        if (ins == null) {
            synchronized (Servants.class) {
                ins = Servants.instance;
                if (ins == null) {
                    Servants.instance = ins = new Servants();
                }
            }
        }
        return ins;
    }

    public void setHanderManager(HandlerManager handlerManager) {
        this.handlerManager = handlerManager;
    }

    private Servants() {
        this.setting = Setting.getInstance();
        this.sqlExecute = new SqlExecute(setting);
        this.fileService = new FileService();
        this.md5 = new MD5();
        this.zip = new Zip();
        this.ftpClient = new FtpClient();
        if (!this.ftpClient.connect(setting.getFtpHost(), setting.getFtpPort())) {
            JOptionPane.showMessageDialog(null,
                    String.format("Ftp: %s - %s connect fail",
                            setting.getFtpHost(), setting.getFtpPort()));
        }
        if (!this.ftpClient.login(setting.getFtpUser(), setting.getFtpPass())) {
            JOptionPane.showMessageDialog(null,
                    String.format("Ftp: %s - %s login fail",
                            setting.getFtpUser(), setting.getFtpPass()));
        }
    }

    public List<String> getListProduct() {
        return this.sqlExecute.getListProduct();
    }

    public List<String> getListStation(String productName) {
        return this.sqlExecute.getListStatus(productName);
    }

    public List<String> getListLine(String productName) {
        return this.sqlExecute.getListLines(productName);
    }

    public synchronized TableListRowParameter getListPcOnServer(boolean only, String product, String Station, String line) {
        TableListRowParameter tableProgram = this.sqlExecute.getListPc(product, Station, line);
        if (only) {
            int index = 0;
            for (List<String> programinfotion : tableProgram.getAll()) {
                if (!this.handlerManager.hasOnline(programinfotion.get(0))) {
                    tableProgram.remove(index);
                } else {
                    index++;
                }
            }
        }
        return tableProgram;
    }

    public boolean isExistsInServer(String pcName) {
        return this.sqlExecute.isPcNameExistsInServer(pcName);
    }

    public synchronized void updatePcInfo(MyName myName, String hostAddress) {
        this.sqlExecute.updatePcInfo(myName, hostAddress);
    }

    public void reUpdateAllProgram(ClientHandler<simplePackage> handler, String project) {
        if (handler == null) {
            return;
        }
        String pcName = this.handlerManager.getID(handler);
        ServerSender sender = (ServerSender) handler.getSender();
        try {
            sender.send(this.sqlExecute.getPackageProgram(pcName, project, new MapRowsParameter(ACTION.UPDATE_ALL_PROGRAM)));
        } catch (SQLException ex) {
            sender.sendMessage("Đã xảy ra lỗi trong quá trình lấy danh sách App từ server\r\n" + ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    public synchronized String addNewPc(String pcNumble, String product, String station, String line) {
        return this.sqlExecute.addNewPc(pcNumble, product, station, line);
    }

    public synchronized void addNewProject(ProjectParameter parameter) throws Exception {
        String projectName = parameter.projectName;
        String programFolder = String.format("%s/%s", this.setting.getFtpProgramFolder(), projectName);
        if (ftpClient.checkFtpDirectoryExists(programFolder)) {
            throw new Exception(String.format("Project: %s has exists!", projectName));
        }
        try {
            this.sqlExecute.addNewProject(projectName, parameter.userID, this.setting.getFtpProgramFolder());
        } catch (SQLException e) {
            throw new Exception(String.format("Upload Program: %s fail!\r\n%s",
                    projectName, e.getLocalizedMessage()));
        }
    }

    public synchronized void addVersionProram(ProgramParameter parameter) throws Exception {
        String projectName = parameter.getProjectName();
        String programName = parameter.getProgramName();
        String version = parameter.getVersion();
        String description = parameter.getDescription();
        String programFolder = String.format("%s/%s/%s/%s",
                this.setting.getFtpProgramFolder(), projectName, programName, version);
        if (ftpClient.checkFtpDirectoryExists(programFolder)) {
            throw new Exception(String.format("Project: %s - program: %s - version: %s has exists!",
                    projectName, programName, version));
        }
        String tempFolder = setting.getTempAppFolder();
        try {
            File localSourceZip = createSourceZip(String.format("%s/%s_%s_%s_Suorce", tempFolder,
                    parameter.getProjectName(), parameter.getProgramName(), parameter.getVersion()),
                    parameter);
            String newFtpSourceFile = String.format("%s/%s/%s/%s/%s", this.setting.getFtpProgramFolder(),
                    projectName, programName, version, localSourceZip.getName());
            File localProgram = createProgramZip(parameter);
            String newFtpProgramFile = String.format("%s/%s/%s/%s/%s", this.setting.getFtpProgramFolder(),
                    projectName, programName, version, localProgram.getName());
            if (!ftpClient.uploadFile(localSourceZip.getPath(), newFtpSourceFile)
                    || !ftpClient.uploadFile(localProgram.getPath(), newFtpProgramFile)) {
                ftpClient.deleteFolder(localSourceZip.getPath());
                throw new Exception("Up ftp file failed!");
            }
            this.sqlExecute.addVersionProram(1, projectName, programName,
                    version, description, this.md5.MD5(localProgram),
                    this.md5.MD5(localSourceZip),
                    localProgram.getName(), localSourceZip.getName());
        } finally {
            fileService.deleteFolder(tempFolder);
        }
    }

    private File createProgramZip(ProgramParameter parameter) throws IOException {

        File programPath = new File(parameter.getProgramPath());
        String projectName = parameter.getProjectName();
        String programName = parameter.getProgramName();
        String version = parameter.getVersion();
        File newFolderProgram = new File(String.format("%s/%s_%s_%s_Program", setting.getTempAppFolder(),
                projectName, programName, version));
        File newName = new File(String.format("%s/%s", newFolderProgram, programName));
        this.fileService.copy(programPath, newName);
        this.fileService.saveFile(String.format("%s/run.bat", newFolderProgram), parameter.getCommandRun());
        String zipFile = String.format("%s.zip", newFolderProgram.getPath());
        this.zip.zipFolder(newFolderProgram.getPath(), zipFile);
        return new File(zipFile);
    }

    private File createSourceZip(String newSuorceFolder, ProgramParameter parameter) throws IOException, Exception {
        this.fileService.copyFilesInDirectory(new File(parameter.getFolderSource()),
                parameter.getLists(), new File(newSuorceFolder));
        String zipFile = String.format("%s.zip", newSuorceFolder);
        this.zip.zipFolder(newSuorceFolder, zipFile);
        return new File(zipFile);
    }

    public synchronized void addVersionConfig(ProgramParameter parameter) throws Exception {
        String projectName = parameter.getProjectName();
        String configName = parameter.getConfigName();
        String version = parameter.getVersion();
        String description = parameter.getDescription();
        int type = parameter.getType();
        String folder = String.format("%s/%s/%s/%s",
                setting.getFtpProgramFolder(), projectName, configName, version);
        if (ftpClient.checkFtpDirectoryExists(folder)) {
            throw new Exception(String.format("Project: %s - config: %s - version: %s has exists!",
                    projectName, configName, version));
        }
        String tempFolder = setting.getTempConfigFolder();
        try {
            File localSourceZip = createSourceZip(String.format("%s/%s_%s_%s_Config", tempFolder,
                    parameter.getProjectName(), parameter.getConfigName(), parameter.getVersion()),
                    parameter);
            String newFtpSourceFile = String.format("%s/%s/%s/%s/%s", this.setting.getFtpProgramFolder(),
                    projectName, configName, version, localSourceZip.getName());
            if (!ftpClient.uploadFile(localSourceZip.getPath(), newFtpSourceFile)) {
                throw new Exception(String.format("Upload %s failed!", newFtpSourceFile));
            }
            sqlExecute.addVersionConfig(1, projectName, configName, version, description,
                    type, this.md5.MD5(localSourceZip), localSourceZip.getName());
        } finally {
            fileService.deleteFolder(tempFolder);
        }
    }

    public TableListRowParameter findAllVersions(String projectName) {
        try {
            return this.sqlExecute.findAllVersions(projectName);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
            return null;
        }
    }

    public TableListRowParameter searchProject(String projectName) throws SQLException {
        return this.sqlExecute.getProject(projectName);
    }

    public TableListRowParameter getListPcMappingProject(boolean hasMapping, String projectName, String productItem, String stationItem, String lineItem) throws SQLException {
        return sqlExecute.getListPcMappingProject(hasMapping, projectName, productItem, stationItem, lineItem);
    }

    public String getProgramNameOfProject(String project) throws SQLException {
        return sqlExecute.getProgramNameOfProject(project);
    }

    public TableListRowParameter getListConfigOfProject(int type, String project) throws SQLException {
        return sqlExecute.getListConfigOfProject(type, project);
    }

    public void mappingProjectWithPC(String pcName, String project, String program,
            Object defaultFolder, Object folder) throws SQLException {
        sqlExecute.mappingProjectWithPC(pcName, project, program, defaultFolder, folder);
    }

    public static class ProjectParameter {

        public ProjectParameter(String projectName, int userID) throws Exception {
            if (projectName == null || projectName.isBlank()) {
                throw new Exception("Project name is empty!");
            }
            this.projectName = projectName;
            this.userID = userID;
        }

        public final String projectName;
        public final int userID;
    }

}
