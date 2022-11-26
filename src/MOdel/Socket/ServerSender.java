/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel.Socket;

import Unicast.commons.AbstractClass.AbsSender;
import Unicast.commons.Actions.Object.ObjectPackage;
import Unicast.commons.Actions.simplePackage;
import Unicast.commons.Enum.ACTION;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class ServerSender extends AbsSender<simplePackage>{
    
    public ServerSender() {
        super();
    }

    public void sendMessage(String mess) {
        if (mess == null || mess .isBlank()) {
            return;
        }
        send(new ObjectPackage<>(ACTION.MESSAGE, mess));
    }

    public boolean ping() {
        return this.send(new simplePackage(ACTION.WHO_ARE_U));
    }

    public void sendWelcome() {
        this.send(new simplePackage(ACTION.WELCOME));
    }
}
