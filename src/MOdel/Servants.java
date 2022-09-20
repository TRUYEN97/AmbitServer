/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel;

import MOdel.Socket.ServerReceiver;
import MOdel.Socket.ServerSender;
import MOdel.Source.Setting;
import MOdel.Sql.SqlExecute;
import Unicast.commons.Actions.Object.MyName;

/**
 *
 * @author Administrator
 */
public class Servants {

    private final Setting setting;
    private final HandlerManager handlerManager;
    private final ServerSender sender;
    private final SqlExecute sqlExecute;

    public Servants(Setting setting) {
        this.setting = setting;
        this.handlerManager = new HandlerManager(setting, this);
        this.sender = new ServerSender();
        this.sqlExecute = new SqlExecute(setting);
    }

    public ServerReceiver getNewReceiver(long id) {
        return new ServerReceiver(id, this);
    }

    public HandlerManager getHandlerManager() {
        return handlerManager;
    }

    public void joinIn(long id, MyName myName) {
        if (this.sqlExecute.checkPcName(myName.getPcName())) {
            long newId = this.sqlExecute.getIdOfPc(myName.getPcName());
            if (newId < 0) {
                return;
            }
            this.handlerManager.setIdentity(id,newId, myName);
        } else {
            this.sender.sendMessager(String.format("%s not set in server!", myName.getPcName()));
        }
    }

    public void updateProgram(long id) {
    }

}
