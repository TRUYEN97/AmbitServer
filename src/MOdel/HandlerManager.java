/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel;

import MOdel.Source.Setting;
import MOdel.Socket.ServerReceiver;
import Unicast.Server.ClientHandler;
import Unicast.commons.Actions.simplePackage;
import Unicast.commons.Enum.ACTION;
import Unicast.commons.Interface.IHandlerManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Administrator
 */
public class HandlerManager implements IHandlerManager<simplePackage> {

    private final ExecutorService pool;
    private final int handlerMax;
    private final Map<Long, ClientHandler<simplePackage>> clientHandlers;
    private final Map<Long, String> PCnames;
    private final Setting setting;

    public HandlerManager(Setting setting) {
        this.handlerMax = setting.getHandlerMax();
        this.pool = Executors.newFixedThreadPool(handlerMax);
        this.clientHandlers = new HashMap<>();
        this.PCnames = new HashMap<>();
        this.setting = setting;
    }

    @Override
    public void shutdown() {
        this.pool.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return this.pool.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return this.pool.isShutdown();
    }

    @Override
    public void add(ClientHandler<simplePackage> handler) {
        final long id = System.currentTimeMillis();
        handler.setObjectAnalysis(new ServerReceiver(id, new Servants(setting), PCnames));
        handler.send(new simplePackage(ACTION.WHO_ARE_U));
        this.clientHandlers.put(id, handler);
        this.pool.execute(handler);
    }

    @Override
    public void disConnect(ClientHandler<simplePackage> handlerName) {
        handlerName.send(new simplePackage(ACTION.GOOD_BYE));
        handlerName.disConnect();
        this.clientHandlers.remove(handlerName);
    }

    @Override
    public boolean disConnect(long ID) {
        if (ID < 0 | ID >= this.clientHandlers.size()) {
            return false;
        }
        this.clientHandlers.get(ID).disConnect();
        return true;
    }

    @Override
    public void disConnectAll() {
        for (ClientHandler clientHandler : clientHandlers.values()) {
            clientHandler.disConnect();
        }
        this.clientHandlers.clear();
    }

    @Override
    public synchronized int getAmountOfClients() {
        return this.clientHandlers.size();
    }

    @Override
    public synchronized int getMaxClint() {
        return this.handlerMax;
    }

    @Override
    public synchronized int getWaitLine() {
        int num = this.clientHandlers.size() - this.handlerMax;
        return num < 0 ? 0 : num;
    }

}
