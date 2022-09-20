/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel.Socket;

import Unicast.Server.ClientHandler;
import Unicast.commons.Actions.Object.MyName;
import Unicast.commons.Actions.simplePackage;

/**
 *
 * @author Administrator
 */
public class handlerCover {

    private final ClientHandler<simplePackage> handler;
    private final long id;
    private MyName myName;

    public handlerCover(long id, ClientHandler<simplePackage> handler) {
        this.handler = handler;
        this.id = id;
    }

    public ClientHandler<simplePackage> getHandler() {
        return handler;
    }

    public long getId() {
        return id;
    }

    public void setMyName(MyName myName) {
        if (myName == null) {
            throw new NullPointerException("MyName cannot be equals null");
        }
        this.myName = myName;
    }

}
