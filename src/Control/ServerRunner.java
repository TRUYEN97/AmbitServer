/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import MOdel.HandlerManager;
import MOdel.Source.Setting;
import Unicast.Server.Server;
import View.Display;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.Timer;

/**
 *
 * @author Administrator
 */
public class ServerRunner implements Runnable{

    private final Display display;
    private final HandlerManager handlerManager;
    private final Server serverSocket;
    private final Timer timer;

    public ServerRunner(Display display, Setting setting) throws IOException {
        this.display = display;
        this.handlerManager = new HandlerManager(setting);
        this.serverSocket = new Server(setting.getPort(), this.handlerManager);
        this.display.setPort(setting.getPort());
        this.timer = new Timer(1000, (ActionEvent e) -> {
            if (ServerRunner.this.display == null) {
                return;
            }
            ServerRunner.this.display.showAmountOfClients(ServerRunner.this.handlerManager.getAmountOfClients());
            ServerRunner.this.display.showWaitLine(ServerRunner.this.handlerManager.getWaitLine());
            ServerRunner.this.display.showMaxClient(ServerRunner.this.handlerManager.getMaxClint());
        });
    }

    public void run() {
        this.serverSocket.start();
        this.timer.start();
        showDisplay();
    }

    private void showDisplay() {
        java.awt.EventQueue.invokeLater(() -> {
            this.display.setVisible(true);
        });
    }
}
