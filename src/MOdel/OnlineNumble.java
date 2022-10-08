/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel;

import Unicast.Server.ClientHandler;
import Unicast.commons.Actions.simplePackage;
import Unicast.commons.Enum.ACTION;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class OnlineNumble {

    private final Map<String, ClientHandler<simplePackage>> onlines;

    public OnlineNumble() {
        this.onlines = new HashMap<>();
    }

    public void disconnect(ClientHandler<simplePackage> handlerName) {
        List<String> keyRemove = new ArrayList<>();
        for (String key : this.onlines.keySet()) {
            if (this.onlines.get(key).equals(handlerName)) {
                keyRemove.add(key);
            }
        }
        for (String key : keyRemove) {
            this.disconnect(key);
        }
    }

    public boolean hasOnline(String pcName) {
        return this.onlines.containsKey(pcName);
    }

    public void setOnline(String pcName, ClientHandler<simplePackage> handler) {
        this.onlines.put(pcName, handler);
    }

    public void disconnect(String pcName) {
        ClientHandler handler = this.onlines.remove(pcName);
        if (handler != null) {
            handler.send(new simplePackage(ACTION.GOOD_BYE));
            handler.disConnect();
        }
    }

    public void disConnectAll() {
        for (String key : this.onlines.keySet()) {
            this.onlines.get(key).disConnect();
        }
        this.onlines.clear();
    }

    public int size() {
        return this.onlines.size();
    }

}
