/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel;

import MOdel.Socket.ServerReceiver;
import MOdel.Socket.ServerSender;
import MOdel.Source.Setting;
import MOdel.Sql.SqlExecute;
import Unicast.Server.ClientHandler;
import Unicast.commons.Actions.Object.MyName;
import Unicast.commons.Actions.simplePackage;
import Unicast.commons.Interface.IObjectReceiver;
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
        this.sender = new ServerSender(this.handlerManager);
        this.sqlExecute = new SqlExecute(setting);
    }

    public HandlerManager getHandlerManager() {
        return handlerManager;
    }

    public void joinInOnline(ClientHandler<simplePackage> handler, MyName myName) {
        if (this.handlerManager.hasOnline(myName.getPcName())) {
            this.sender.sendMessage(handler, String.format("Pc name: %s has online", myName.getPcName()));
            handler.disConnect();
        } else if (!isExistsInServer(myName.getPcName())) {
            this.sender.sendMessage(handler, String.format("Pc name: %s not set in server", myName.getPcName()));
            handler.disConnect();
        } else {
            updatePcInfo(myName);
            this.handlerManager.setOnline(myName.getPcName(), handler);
        }
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

    public void setPcOffline(String pcName) {
        this.sqlExecute.setPcStatus(pcName, SqlExecute.OFF);
    }

    IObjectReceiver<simplePackage> getNewReceiver(ClientHandler<simplePackage> handler) {
        return new ServerReceiver(handler, this);
    }

    private boolean isExistsInServer(String pcName) {
        return this.sqlExecute.isPcNameExistsInServer(pcName);
    }

    private void updatePcInfo(MyName myName) {
        this.sqlExecute.updatePcInfo(myName);
    }

    public void updateProgram(ClientHandler<simplePackage> handler) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
