/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel;

import Unicast.Server.ClientHandler;
import Unicast.commons.Actions.simplePackage;
import java.awt.event.ActionEvent;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import javax.swing.Timer;

/**
 *
 * @author Administrator
 */
public class WaitAccept {
    
    private final Queue<ClientHandler<simplePackage>> queueAccept;
    private final Map<Long,ClientHandler<simplePackage>> mapAccept;
    private final Timer timer;
    private final Servants servants;

    public WaitAccept(Servants servants, int waitMax) {
        this.queueAccept = new ArrayDeque<>(waitMax);
        this.servants = servants;
        this.mapAccept = new HashMap();
        this.timer = new Timer(2000, (ActionEvent e) -> {
            ClientHandler<simplePackage> handler;
            synchronized (WaitAccept.this.queueAccept) {
                if (WaitAccept.this.queueAccept.isEmpty() || (handler = WaitAccept.this.queueAccept.poll()) == null) {
                    WaitAccept.this.timer.stop();
                    return;
                }
                handler.disConnect();
            }
        });
    }

    public void add( ClientHandler<simplePackage> handler) {
        long id = System.currentTimeMillis();
        this.queueAccept.add(handler);
        this.mapAccept.put(id, handler);
        handler.setObjectAnalysis(this.servants.getNewReceiver(handler));
    }

    public ClientHandler<simplePackage> getClientHandler(long id) {
        return this.mapAccept.get(id);
    }
    
}
