/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel;

import MOdel.Source.Setting;
import Unicast.Server.ClientHandler;
import Unicast.commons.Actions.simplePackage;
import Unicast.commons.Interface.IHandlerManager;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Administrator
 */
public class HandlerManager implements IHandlerManager<simplePackage> {

    private final ExecutorService pool;
    private final int handlerMax;
    private final int waitMax;
    private final OnlineNumble onlineNumble;
    private final Servants servants;
    private final WaitAccept waitAccept;

    public HandlerManager(Setting setting, Servants servants) {
        this.handlerMax = setting.getHandlerMax();
        this.waitMax = setting.getWaitMax();
        this.pool = Executors.newFixedThreadPool(handlerMax);
        this.waitAccept = new WaitAccept(servants, waitMax);
        this.onlineNumble = new OnlineNumble();
        this.servants = servants;
    }

    @Override
    public void shutdown() {
        this.pool.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return this.pool.shutdownNow();
    }

    public boolean isShutdown() {
        return this.pool.isShutdown();
    }

    @Override
    public void add(ClientHandler<simplePackage> handler) {
        this.waitAccept.add(handler);
        this.pool.execute(handler);
    }

    @Override
    public void disConnect(ClientHandler<simplePackage> handlerName) {
        this.onlineNumble.disconnect(handlerName);
    }

    @Override
    public void disConnect(String pcName) {
       this.onlineNumble.disconnect(pcName);
    }

    @Override
    public void disConnectAll() {
        this.onlineNumble.disConnectAll();
    }
    
    public synchronized int getAmountOfClients() {
        return this.onlineNumble.size() - this.handlerMax;
    }

    public synchronized int getMaxClient() {
        return this.handlerMax;
    }

    public synchronized int getWaitLine() {
        int num = getAmountOfClients() - this.handlerMax;
        return num < 0 ? 0 : num;
    }
   
    
    public ClientHandler<simplePackage> getWaitAccept(long id) {
      return this.waitAccept.getClientHandler(id);
    }

    public boolean hasOnline(String pcName) {
        return this.onlineNumble.hasOnline(pcName);
    }

    public void setOnline(String pcName, ClientHandler<simplePackage> handler) {
        this.onlineNumble.setOnline(pcName, handler);
    }

}
