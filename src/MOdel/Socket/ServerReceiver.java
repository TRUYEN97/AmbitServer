/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel.Socket;

import MOdel.Servants;
import Unicast.commons.Actions.Object.MyName;
import Unicast.commons.Actions.simplePackage;
import Unicast.commons.Interface.IObjectReceiver;

/**
 *
 * @author Administrator
 */
public class ServerReceiver implements IObjectReceiver<simplePackage> {

    private final Servants servicer;
    private final long id;

    public ServerReceiver(long id, Servants servicer) {
        this.id = id;
        this.servicer = servicer;
    }

    @Override
    public void receiver(simplePackage pg) {
        switch (pg.getAction()) {
            case I_AM -> {
                if (pg instanceof MyName myName) {
                   this.servicer.joinIn(id, myName);
                }
            }
            
            case DOWN_LOAD -> {
            }
            
            case UPDATE -> {
                this.servicer.updateProgram(id);
            }
            default -> {
            }
        }
    }

}
