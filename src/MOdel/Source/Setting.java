/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel.Source;

import FileTool.FileService;
import MOdel.AllKeyword;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.nio.file.FileSystemNotFoundException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Setting {

    private static volatile Setting instance;
    private final DataWareHouse wareHouse;
    private final FileService fileService;

    private Setting() {
        this.wareHouse = new DataWareHouse();
        this.fileService = new FileService();
        init(new File("config/Setting/setting.json"));
    }

    public static Setting getInstance() {
        Setting ins = Setting.instance;
        if (ins == null) {
            synchronized (Setting.class) {
                ins = Setting.instance;
                if (Setting.instance == null) {
                    Setting.instance = ins = new Setting();
                }
            }
        }
        return ins;
    }

    private void init(File file) {
        if (!file.exists()) {
            throw new FileSystemNotFoundException(file.getPath());
        }
        try {
            wareHouse.clear();
            wareHouse.putAll(JSONObject.parseObject(fileService.readFile(file)));
        } catch (Exception e) {
            throw new RuntimeException("init setting failed");
        }
    }

    public Integer getInteger(String key) {
        return this.wareHouse.getInteger(key);
    }

    public String getString(String key) {
        return this.wareHouse.getString(key);
    }

    public String getVersion() {
        return getString(AllKeyword.VERSION);
    }

    public int getPort() {
        return getInteger(AllKeyword.SERVER_PORT);
    }

    public int getHandlerMax() {
        return getInteger(AllKeyword.MAX_HANDLER);
    }

    boolean pcIsContain(String pcName, String PN) {
        return this.getPNs(pcName).contains(PN);
    }

    List<String> getPNs(String pcName) {
        return this.wareHouse.getListJsonArray(pcName);
    }

    List<String> getPathFile(String pcName, String PN) {
        return this.wareHouse.getListJsonArray(PN);
    }

    public String getDatabase() {
        return getString(AllKeyword.DATA_BASE);
    }

    public String getSqlUser() {
        return getString(AllKeyword.SQL_USER);
    }

    public String getSqlPass() {
        return getString(AllKeyword.SQL_PASS);
    }

    public int getWaitMax() {
        return getInteger(AllKeyword.MAX_WAIT);
    }

    public String getIcon() {
        return getString(AllKeyword.ICON);
    }

    public String getProgramFolder() {
        return getString(AllKeyword.PROGRAMS_PATH);
    }

    public String getFtpHost() {
        return getString(AllKeyword.FTP.HOST);
    }

    public int getFtpPort() {
        return getInteger(AllKeyword.FTP.PORT);
    }

    public String getFtpUser() {
        return getString(AllKeyword.FTP.USER);
    }

    public String getFtpPass() {
        return getString(AllKeyword.FTP.PASS);
    }
}
