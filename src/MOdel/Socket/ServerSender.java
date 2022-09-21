/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel.Socket;

import MOdel.HandlerManager;

/**
 *
 * @author Administrator
 */
public class ServerSender {

    private final HandlerManager handlerManager;

    public ServerSender(HandlerManager handlerManager) {
        this.handlerManager = handlerManager;
    }

    public void sendMessager(long id, String string) {
        System.out.println(string);
    }

}
