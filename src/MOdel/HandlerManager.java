/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel;

import MOdel.Socket.ServerSender;
import MOdel.Source.Setting;
import Unicast.Server.ClientHandler;
import Unicast.Server.Handlermanage.DefaultServerHandleManagement;
import Unicast.commons.Actions.simplePackage;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class HandlerManager extends DefaultServerHandleManagement<String, simplePackage> {

    public HandlerManager() {
        super(new OnlineNumbles(), new ServerSender(), new ConfigAccept(),
                Setting.getInstance().getHandlerMax(), 
                Setting.getInstance().getWaitMax(), 1000);
    }

    public List<Runnable> shutdownNow() {
        return this.pool.shutdownNow();
    }

    public boolean isShutdown() {
        return this.pool.isShutdown();
    }

    public synchronized int getAmountOfClients() {
        return this.onlineNumble.size();
    }

    public synchronized int getMaxClient() {
        return this.max;
    }

    public synchronized int getWaitLine() {
        int num = getAmountOfClients() - this.max;
        return num < 0 ? 0 : num;
    }

    public ClientHandler<simplePackage> getWaitAccept(long id) {
        return this.waitAccept.getClientHandler(id);
    }

}
