/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import MOdel.Servants;
import MOdel.Source.Setting;
import View.Display;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class Engine {

    private final Display display;
    private final Core core;

    public Engine() throws SQLException {
        Setting setting = new Setting("Setting/setting.json");
        this.display = new Display(setting.getVersion());
        this.core = new Core(this.display, new Servants(setting), setting);
    }

    public void run() {
        this.core.run();
        this.display.display();
    }

}
