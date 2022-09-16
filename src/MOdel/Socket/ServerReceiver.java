/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel.Socket;

import FileTool.FileService;
import MOdel.Servants;
import Unicast.commons.Actions.Object.MyName;
import Unicast.commons.Actions.simplePackage;
import Unicast.commons.Interface.ISend;
import java.util.Map;
import Unicast.commons.Interface.IObjectReceiver;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class ServerReceiver implements IObjectReceiver<simplePackage> {

    private ISend<simplePackage> handler;
    private final Servants servicer;
    private final long id;
    private final Map<Long, String> PCnames;
    private final FileService fileService;

    public ServerReceiver(long id, Servants servicer, Map<Long, String> PCnames) {
        this.id = id;
        this.servicer = servicer;
        this.PCnames = PCnames;
        this.fileService = new FileService();
    }

    @Override
    public void receiver(simplePackage pg) {
        switch (pg.getAction()) {
            case I_AM -> {
                if (pg instanceof MyName myName) {
                    this.PCnames.put(id, myName.getPcName());
                    System.out.println(myName.getdata());
                }
            }
            case DOWN_LOAD -> {
                Map<String,String> files = new HashMap<>();
//                files.put(key, value);
            }
            case UPDATE -> {
                
            }
            default -> {
            }
        }
    }

    @Override
    public void setHandler(ISend<simplePackage> handler) {
        this.handler = handler;
    }

}
