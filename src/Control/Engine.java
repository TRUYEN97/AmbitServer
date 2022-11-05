/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import MOdel.Servants;
import MOdel.Source.Setting;
import SystemTray.MySystemTray;
import View.Display;
import java.awt.AWTException;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class Engine {

    private final Display display;
    private final Core core;
    private final Servants servants;
    private final MySystemTray systemTray;

    public Engine() throws SQLException, Exception {
        Setting setting = Setting.getInstance();
        this.servants = new Servants(setting);
        this.display = new Display(setting.getVersion(), servants);
        this.core = new Core(this.display, servants, setting);
        this.systemTray = new MySystemTray(display);
        this.systemTray.initTrayIcon(setting.getIcon(), "Server");
    }

    public void run() throws AWTException {
        this.core.run();
        this.display.display();
        this.systemTray.apply();
    }

}
