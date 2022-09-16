/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import MOdel.Socket.ServerReceiver;
import MOdel.Socket.ServerSender;
import MOdel.Source.Setting;
import View.Display;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class Core {

    private final ServerRunner runner;
    private final Display display;
    private final Setting setting;

    public Core(Display display, Setting setting) throws IOException {
        this.runner = new ServerRunner(display, setting);
        this.display = display;
        this.setting = setting;
    }

    public void run() {
        new Thread(runner).start();
    }
    
}
