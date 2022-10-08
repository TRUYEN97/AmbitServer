/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import MOdel.HandlerManager;
import MOdel.Servants;
import MOdel.Source.Setting;
import Unicast.Server.Server;
import View.Display;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

/**
 *
 * @author Administrator
 */
public class ServerRunner implements Runnable {

    private final Display display;
    private final HandlerManager handlerManager;
    private Server serverSocket;
    private final Timer timer;
    private final Thread thread;

    public ServerRunner(Display display, Servants servants, Setting setting) {
        this.display = display;
        this.handlerManager = servants.getHandlerManager();
        try {
            this.serverSocket = new Server(setting.getPort(), this.handlerManager);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        this.display.setPort(setting.getPort());
        this.timer = new Timer(1000, (ActionEvent e) -> {
            if (ServerRunner.this.display == null) {
                return;
            }
            ServerRunner.this.display.showAmountOfClients(ServerRunner.this.handlerManager.getAmountOfClients());
            ServerRunner.this.display.showWaitLine(ServerRunner.this.handlerManager.getWaitLine());
            ServerRunner.this.display.showMaxClient(ServerRunner.this.handlerManager.getMaxClient());
        });
        this.thread = new Thread(this);
    }

    public void start() {
        this.thread.start();
    }

    public void stop() {
        this.thread.stop();
    }

    @Override
    public void run() {
        this.serverSocket.start();
        this.timer.start();
    }
}
