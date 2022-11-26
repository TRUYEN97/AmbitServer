/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel;

import MOdel.Socket.ServerReceiver;
import MOdel.Socket.ServerSender;
import Unicast.Server.ClientHandler;
import Unicast.Server.Handlermanage.Online.OnlineNumble;
import Unicast.commons.Actions.simplePackage;

/**
 *
 * @author Administrator
 */
public class OnlineNumbles extends OnlineNumble<String, simplePackage> {

    public OnlineNumbles() {
        super(new ServerReceiver());
    }

    public boolean ping(String pcName) {
        ClientHandler<simplePackage> handler = getClientHandler(pcName);
        if (handler == null) {
            return false;
        }
        ServerSender sender = (ServerSender) handler.getSender();
        return sender.ping();
    }

}
