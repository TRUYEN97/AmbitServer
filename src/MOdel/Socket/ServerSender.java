/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel.Socket;

import MOdel.HandlerManager;
import Unicast.Server.ClientHandler;
import Unicast.commons.Actions.Object.MyName;
import Unicast.commons.Actions.Object.ObjectPackage;
import Unicast.commons.Actions.simplePackage;
import Unicast.commons.Enum.ACTION;

/**
 *
 * @author Administrator
 */
public class ServerSender {

    private final HandlerManager handlerManager;

    public ServerSender(HandlerManager handlerManager) {
        this.handlerManager = handlerManager;
    }

    public void sendMessage(ClientHandler<simplePackage> handler, String mess) {
        if (handler == null || mess == null) {
            return;
        }
        handler.send(new ObjectPackage<>(ACTION.MESSAGE, mess));
    }
}
