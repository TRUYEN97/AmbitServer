/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel;

import Control.Servants;
import MOdel.Socket.ServerSender;
import Unicast.Server.ClientHandler;
import Unicast.Server.Handlermanage.WaitLine.AbsConfirmAccept;
import Unicast.commons.Actions.Object.MyName;
import Unicast.commons.Actions.simplePackage;
import Unicast.commons.Enum.ACTION;

/**
 *
 * @author Administrator
 */
public class ConfigAccept extends AbsConfirmAccept<String, simplePackage> {

    private final Servants servants;

    public ConfigAccept() {
        super();
        this.servants = Servants.getInstance();
    }

    @Override
    protected String acceptable(ClientHandler<simplePackage> handler, simplePackage object) {
        if (object.getAction().equals(ACTION.I_AM) && object instanceof MyName myName ) {
            if (this.onlineNumble.hasOnline(myName.getPcName())) {
                ClientHandler clientHandler = this.onlineNumble.getClientHandler(myName.getPcName());
                if (clientHandler.equals(handler)) {
                    servants.updatePcInfo(myName, handler.getHostAddress());
                    return null;
                }
                ServerSender sender = (ServerSender) handler.getSender();
                sender.sendMessage(String.format("Pc name: %s has online", myName.getPcName()));
                handler.disConnect();
            } else if (!servants.isExistsInServer(myName.getPcName())) {
                ServerSender sender = (ServerSender) handler.getSender();
                sender.sendMessage(String.format("Pc name: %s not set in server", myName.getPcName()));
                handler.disConnect();
            } else {
                servants.updatePcInfo(myName, handler.getHostAddress());
                ((ServerSender)handler.getSender()).sendWelcome();
                return myName.getPcName();
            }
        }
        return null;
    }

}
