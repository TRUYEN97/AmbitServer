/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel.Socket;

import Control.Servants;
import Unicast.Server.ClientHandler;
import Unicast.commons.AbstractClass.AbsServerReceiver;
import Unicast.commons.Actions.MapRowsParameter;
import Unicast.commons.Actions.Object.MyName;
import Unicast.commons.Actions.UpdateProject;
import Unicast.commons.Actions.simplePackage;

/**
 *
 * @author Administrator
 */
public class ServerReceiver extends AbsServerReceiver<simplePackage> {

    private final Servants servicer;

    public ServerReceiver() {
        this.servicer = Servants.getInstance();
    }

    @Override
    public void receiver(ClientHandler<simplePackage> handler, simplePackage pg) {
        switch (pg.getAction()) {
            case I_AM -> {
                if (pg instanceof MyName myName) {
                    this.servicer.updatePcInfo(myName, handler.getHostAddress());
                }
            }

            case UPDATE_PROGRAM -> {
                if (pg instanceof UpdateProject project) {
                    this.servicer.reUpdateAllProgram(handler, project.getProjectName());
                }
            }

            case UPDATE_ALL_PROGRAM -> {
                this.servicer.reUpdateAllProgram(handler, "all");
            }
        }
    }

}
