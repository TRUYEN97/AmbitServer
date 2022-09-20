/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import MOdel.Servants;
import MOdel.Source.Setting;
import View.Display;

/**
 *
 * @author Administrator
 */
public class Core {

    private final ServerRunner runner;
    private final Servants servants;
    private final Display display;
    private final Setting setting;

    public Core(Display display, Servants servants, Setting setting) {
        this.servants = servants;
        this.runner = new ServerRunner(display, servants, setting);
        this.display = display;
        this.setting = setting;
    }

    public void run() {
        this.runner.start();
        showDisplay();
    }

    private void showDisplay() {
        java.awt.EventQueue.invokeLater(() -> {
            this.display.setVisible(true);
        });
    }
}
