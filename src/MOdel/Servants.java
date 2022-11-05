/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel;

import Communicate.FtpClient.FtpClient;
import FileTool.FileService;
import MOdel.Socket.ServerReceiver;
import MOdel.Socket.ServerSender;
import MOdel.Source.Setting;
import MOdel.Sql.SqlExecute;
import Unicast.Server.ClientHandler;
import Unicast.commons.Actions.Object.MyName;
import Unicast.commons.Actions.simplePackage;
import Unicast.commons.Interface.IObjectReceiver;
import View.Other.ProcessForm;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class Servants {

    private final Setting setting;
    private final HandlerManager handlerManager;
    private final ServerSender sender;
    private final SqlExecute sqlExecute;
    private final FileService fileService;
    private final FtpClient ftpClient;

    public Servants(Setting setting) throws Exception {
        this.setting = setting;
        this.handlerManager = new HandlerManager(setting, this);
        this.sender = new ServerSender(this.handlerManager);
        this.sqlExecute = new SqlExecute(setting);
        this.fileService = new FileService();
        this.ftpClient = new FtpClient();
        if (!this.ftpClient.connect(setting.getFtpHost(), setting.getFtpPort())) {
            throw new Exception(String.format("Ftp: %s - %s connect fail", setting.getFtpHost(), setting.getFtpPort()));
        }
        if (!this.ftpClient.login(setting.getFtpUser(), setting.getFtpPass())) {
            throw new Exception(String.format("Ftp: %s - %s login fail", setting.getFtpUser(), setting.getFtpPass()));
        }
    }

    public HandlerManager getHandlerManager() {
        return handlerManager;
    }

    public void joinInOnline(ClientHandler<simplePackage> handler, MyName myName) {
        if (this.handlerManager.hasOnline(myName.getPcName())) {
            this.sender.sendMessage(handler, String.format("Pc name: %s has online", myName.getPcName()));
            handler.disConnect();
        } else if (!isExistsInServer(myName.getPcName())) {
            this.sender.sendMessage(handler, String.format("Pc name: %s not set in server", myName.getPcName()));
            handler.disConnect();
        } else {
            updatePcInfo(myName, handler.getHostAddress());
            this.handlerManager.setOnline(myName.getPcName(), handler);
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

    public synchronized TableParameter getListPcOnServer(boolean only, String product, String Station, String line) {
        TableParameter tableProgram = this.sqlExecute.getListPc(product, Station, line);
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

    public IObjectReceiver<simplePackage> getNewReceiver(ClientHandler<simplePackage> handler) {
        return new ServerReceiver(handler, this);
    }

    private boolean isExistsInServer(String pcName) {
        return this.sqlExecute.isPcNameExistsInServer(pcName);
    }

    private synchronized void updatePcInfo(MyName myName, String hostAddress) {
        this.sqlExecute.updatePcInfo(myName, hostAddress);
    }

    public void updateProgram(ClientHandler<simplePackage> handler) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public synchronized String addNewPc(String pcNumble, String product, String station, String line) {
        return this.sqlExecute.addNewPc(pcNumble, product, station, line);
    }

    public synchronized void addNewProject(ProjectParameter parameter) throws Exception {
        String projectName = parameter.projectName;
        String programFolder = String.format("%s/%s", this.setting.getProgramFolder(), projectName);
        if (ftpClient.checkFtpDirectoryExists(programFolder)) {
            throw new Exception(String.format("Project: %s has exists!",
                    projectName));
        }
        try {
            this.sqlExecute.addNewProject(projectName, parameter.userID, programFolder);
        } catch (SQLException e) {
            throw new Exception(String.format("Upload Program: %s fail!\r\n%s",
                    projectName, e.getLocalizedMessage()));
        }
    }

    public synchronized void addVersionProram(ProgramParameter parameter) throws Exception {
        String projectName = parameter.projectName;
        String programName = parameter.programName;
        String version = parameter.version;
        String description = parameter.description;
        File program = new File(parameter.programFile);
        String programFolder = String.format("%s/%s/%s/%s",
                this.setting.getProgramFolder(), projectName, programName, version);
        if (ftpClient.checkFtpDirectoryExists(programFolder)) {
            throw new Exception(String.format("Project: %s - program: %s - version: %s has exists!",
                    projectName, programName, version));
        }
        try {
            if (!ftpClient.uploadFile(program.getPath(), String.format("%s/%s", programFolder, program.getName()))
                    || !ftpClient.upStringToFTP(createdRunFile(parameter), String.format("%s/run.bat", programFolder))) {
                this.ftpClient.deleteFolder(programFolder);
            } else {
                this.sqlExecute.addVersionProram(1, projectName, programName, version, description);
            }
        } catch (SQLException e) {
            this.ftpClient.deleteFolder(programFolder);
            this.sqlExecute.removeVersionProgram(projectName, programName, version);
            throw new Exception(String.format("Upload Program: %s - version: %s-%s fail!\r\n%s",
                    projectName, programName, version, e.getLocalizedMessage()));
        }
    }

    public synchronized void addVersionConfig(ConfigFolderParameter parameter) throws Exception {
        String projectName = parameter.projectName;
        String configName = parameter.configName;
        String version = parameter.version;
        String description = parameter.description;
        String folder = String.format("%s/%s/%s/%s",
                setting.getProgramFolder(), projectName, configName, version);
        if (ftpClient.checkFtpDirectoryExists(folder)) {
            throw new Exception(String.format("Project: %s - config: %s - version: %s has exists!",
                    projectName, configName, version));
        }
        copyToWawehouse(configName, parameter.lists, folder, parameter.folderSource);
        sqlExecute.addVersionConfig(1, projectName, configName, version, description);
    }

    public TableParameter findAllVersions(String projectName) {
        try {
            return this.sqlExecute.findAllVersions(projectName);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
            return null;
        }
    }

    private void copyToWawehouse(String name, List<File> files, String programFolder, String folderSource) {
        ProcessForm processForm = new ProcessForm(files.size(), name);
        processForm.display();
        try {
            int sum = 0;
            for (File filesource : files) {
                if (!filesource.exists()) {
                    processForm.appendLine(String.format("file: %s not exists!", filesource));
                    ftpClient.deleteFolder(programFolder);
                    break;
                }
                if (processForm.isCloseed()) {
                    ftpClient.deleteFolder(programFolder);
                    break;
                }
                String source = filesource.getPath();
                String newFile = String.format("%s/%s",
                        programFolder, source.substring(source.indexOf(folderSource) + folderSource.length()));
                if (ftpClient.uploadFile(source, newFile)) {
                    processForm.setValueProcessbar(++sum);
                    processForm.appendLine(String.format("%s - ok", newFile));
                } else {
                    ftpClient.deleteFolder(programFolder);
                    break;
                }
            }
        } finally {
            processForm.stop();
        }
    }

    private String createdRunFile(ProgramParameter parameter) throws Exception {
        String programName = new File(parameter.programFile).getName();
        if (programName.toLowerCase().matches("^.+\\.jar$")) {
            return String.format("start java -jar %s", programName);
        } else if (programName.toLowerCase().matches("^.+\\.exe$") || programName.toLowerCase().matches("^.+\\.py$")) {
            return String.format("start %s", programName);
        } else {
            throw new Exception("program name unknow!");

        }
    }

    public TableParameter searchProject(String projectName) throws SQLException {
        return this.sqlExecute.getProject(projectName);
    }

    public TableParameter getListPcMappingProject(boolean hasMapping, String projectName, String productItem, String stationItem, String lineItem) throws SQLException {
        return sqlExecute.getListPcMappingProject(hasMapping, projectName, productItem, stationItem, lineItem);
    }

    public String getProgramNameOfProject(String project) throws SQLException {
        return sqlExecute.getProgramNameOfProject(project);
    }
    
    public TableParameter getListConfigOfProject(int type, String project) throws SQLException {
        return sqlExecute.getListConfigOfProject(type, project);
    }

    public void mappingProjectWithPC(String pcName, String project, String program,
            Object defaultFolder, Object folder) throws SQLException {
        sqlExecute.mappingProjectWithPC(pcName, project, program, defaultFolder, folder);
    }

    public static class ProgramParameter {

        public ProgramParameter(String projectName, String programName,
                String version, String description, String programFile) throws Exception {

            if (programFile == null || programFile.isBlank()) {
                throw new Exception("please! add a program!");
            }
            if (projectName == null || projectName.isBlank()) {
                throw new Exception("Project name is empty!");
            }
            if (programName == null || programName.isBlank()) {
                throw new Exception("program name is empty!");
            }
            if (version == null || version.isBlank()) {
                throw new Exception("Project version is empty!");
            }
            if (description == null || description.isBlank()) {
                throw new Exception("Project description is empty!");
            }
            this.projectName = projectName;
            this.programName = programName;
            this.version = version;
            this.description = description;
            this.programFile = programFile;
        }
        public final String projectName;
        public final String programName;
        public final String version;
        public final String description;
        public final String programFile;
    }

    public static class ConfigFolderParameter {

        public ConfigFolderParameter(String projectName, String configName,
                String version, String description,
                String folderSource, List<File> lists) throws Exception {
            if (projectName == null || projectName.isBlank()) {
                throw new Exception("Project name is empty!");
            }
            if (configName == null || configName.isBlank()) {
                throw new Exception("Config name is empty!");
            }
            if (version == null || version.isBlank()) {
                throw new Exception(String.format("Config version of %s is empty!", configName));
            }
            if (description == null || description.isBlank()) {
                throw new Exception(String.format("Description of %s is empty!", configName));
            }
            if (lists == null) {
                throw new Exception(String.format("List file of %s is null", configName));
            }
            this.projectName = projectName;
            this.configName = configName;
            this.version = version;
            this.description = description;
            this.folderSource = folderSource;
            this.lists = lists;
        }

        public final String projectName;
        public final String configName;
        public final String version;
        public final String description;
        public final String folderSource;
        public final List<File> lists;
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
