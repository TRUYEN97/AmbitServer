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
import java.util.List;

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
        this.sender = new ServerSender( this.handlerManager);
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
            this.sqlExecute.setPcInfomation(id, myName);
            this.sqlExecute.setPcStatus(id, true);
            this.handlerManager.setIdentity(id,newId, myName);
        } else {
            this.sender.sendNotSetPC(id, myName);
        }
    }

    public void updateProgram(long id) {
    }

    public List<String> getListProduct() {
        return this.sqlExecute.getListProduct();
    }

    public List<String> getListStation(String productName) {
        return this.sqlExecute.getListStatus(productName);
    }

    public List<String> getListLine(String productName) {
        return this.sqlExecute.getListLines(productName); 
    }

    public List<String> getListPcOnline(String product, String Station, String line) {
        return this.sqlExecute.getListPcOnline(product, Station, line);
    }

}
