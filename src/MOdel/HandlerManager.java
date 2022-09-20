/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel;

import MOdel.Socket.ServerReceiver;
import MOdel.Socket.handlerCover;
import MOdel.Source.Setting;
import Unicast.Server.ClientHandler;
import Unicast.commons.Actions.Object.MyName;
import Unicast.commons.Actions.simplePackage;
import Unicast.commons.Enum.ACTION;
import Unicast.commons.Interface.IHandlerManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.Timer;

/**
 *
 * @author Administrator
 */
public class HandlerManager implements IHandlerManager<simplePackage> {

    private final ExecutorService pool;
    private final int handlerMax;
    private final Queue<handlerCover> waitAccept;
    private final Map<Long, handlerCover> clientHandlers;
    private final Servants servants;
    private final Timer timer;

    public HandlerManager(Setting setting, Servants servants) {
        this.handlerMax = setting.getHandlerMax();
        this.pool = Executors.newFixedThreadPool(handlerMax);
        this.waitAccept = new LinkedList<>();
        this.clientHandlers = new HashMap<>();
        this.servants = servants;
        this.timer = new Timer(1000, (ActionEvent e) -> {
            handlerCover cover;
            synchronized (HandlerManager.this.waitAccept) {
                if (HandlerManager.this.waitAccept.isEmpty() || (cover = HandlerManager.this.waitAccept.poll()) == null) {
                    HandlerManager.this.timer.stop();
                    return;
                }
                cover.getHandler().disConnect();
            }
        });
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
        long id = System.currentTimeMillis();
        this.waitAccept.add(new handlerCover(id, handler));
        handler.setObjectAnalysis(this.servants.getNewReceiver(id));
        this.pool.execute(handler);
        if (!this.timer.isRunning()) {
            this.timer.start();
        }
    }

    @Override
    public void disConnect(ClientHandler<simplePackage> handlerName) {
        handlerName.send(new simplePackage(ACTION.GOOD_BYE));
        handlerName.disConnect();
        List<Long> keyRemove = new ArrayList<>();
        for (Map.Entry<Long, handlerCover> handler : clientHandlers.entrySet()) {
            if (handler.getValue().getHandler().equals(handlerName)) {
                keyRemove.add(handler.getKey());
            }
        }
        for (Long id : keyRemove) {
            this.clientHandlers.remove(id);
        }
    }

    @Override
    public boolean disConnect(long ID) {
        if (ID < 0 | ID >= this.clientHandlers.size()) {
            return false;
        }
        this.clientHandlers.get(ID).getHandler().disConnect();
        this.clientHandlers.remove(ID);
        return true;
    }

    @Override
    public void disConnectAll() {
        for (handlerCover cover : clientHandlers.values()) {
            cover.getHandler().disConnect();
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
   

    void setIdentity(long waitId, long newID, MyName myName) {
        handlerCover cover = getWaitAccept(waitId);
        if (cover == null) {
            return;
        }
        cover.setMyName(myName);
        this.clientHandlers.put(newID, cover);
    }

    handlerCover getWaitAccept(long id) {
        for (handlerCover cover : waitAccept) {
            if (cover.getId() == id) {
                waitAccept.remove(cover);
                return cover;
            }
        }
        return null;
    }

}
