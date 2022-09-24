/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel.Socket;

import MOdel.HandlerManager;
import Unicast.commons.Actions.Object.MyName;
import Unicast.commons.Actions.Object.ObjectPackage;
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

    public void sendNotSetPC(long id, MyName myName) {
        handlerCover cover = this.handlerManager.takeOutWaitAccept(id);
        if (cover == null) {
            return;
        }
        cover.getHandler().send(new ObjectPackage<>(ACTION.MESSAGE,
                String.format("%s not set in server!", myName.getPcName())));
        cover.getHandler().disConnect();
    }

    public void sendMessage(long id, String mess) {
        handlerCover cover = this.handlerManager.getHandler(id);
        if (cover == null) {
            return;
        }
        cover.getHandler().send(new ObjectPackage<>(ACTION.MESSAGE, mess));
    }
}
