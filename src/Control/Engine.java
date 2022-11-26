/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import MOdel.Source.Setting;
import SystemTray.MySystemTray;
import View.Display;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class Engine {

    private final Display display;
    private final ServerRunner runner;
    private final Servants servants;
    private final MySystemTray systemTray;

    public Engine() throws SQLException, Exception {
        this.servants = Servants.getInstance();
        this.display = new Display();
        this.runner = new ServerRunner(display);
        this.systemTray = new MySystemTray(display);
        this.systemTray.initTrayIcon(Setting.getInstance().getIcon(), "Server");
        this.systemTray.addMenuItem("Exit", new MenuShortcut(KeyEvent.VK_Q), (ActionEvent e) -> {
            System.exit(0);
        });
    }

    public void run() throws AWTException {
        this.runner.run();
        this.display.display();
        this.systemTray.apply();
    }

}
