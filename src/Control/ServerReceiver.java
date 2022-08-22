/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import FileTool.FileService;
import MOdel.Servicer;
import Unicast.commons.Actions.FileTransfer;
import Unicast.commons.Actions.ListPackage;
import Unicast.commons.Actions.MyName;
import Unicast.commons.Actions.ObjectPackage;
import Unicast.commons.Actions.SimplePackage;
import Unicast.commons.Enum.ACTION;
import Unicast.commons.Interface.ISend;
import java.util.Map;
import Unicast.commons.Interface.IObjectReceiver;
import java.io.File;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ServerReceiver implements IObjectReceiver<SimplePackage> {

    private ISend<SimplePackage> handler;
    private final Servicer servicer;
    private final long id;
    private final Map<Long, String> PCnames;
    private final FileService fileService;

    public ServerReceiver(long id, Servicer servicer, Map<Long, String> PCnames) {
        this.id = id;
        this.servicer = servicer;
        this.PCnames = PCnames;
        this.fileService = new FileService();
    }

    @Override
    public void receiver(SimplePackage pg) {
        switch (pg.getAction()) {
            case I_AM -> {
                if (pg instanceof MyName myName) {
                    this.PCnames.put(id, myName.getPcName());
                    System.out.println(myName.getdata());
                    this.handler.send(new ListPackage<>(ACTION.PNs, this.servicer.getListPN(this.PCnames.get(this.id))));
                }
            }
            case PNs -> {
                this.handler.send(new ListPackage<>(ACTION.PNs, this.servicer.getListPN(this.PCnames.get(this.id))));
            }
            case UP_DATE -> {
                if (pg instanceof ObjectPackage) {
                    String PN = ((ObjectPackage<String>) pg).getdata();
                    if (this.servicer.isHavePN(this.PCnames.get(this.id), PN)) {
                        List<String> filePaths = this.servicer.getListFile(this.PCnames.get(this.id), PN);
                        for (String path : filePaths) {
                            System.out.println(path);
                            if (!new File(path).exists()) {
                                continue;
                            }
                            this.handler.send(new FileTransfer(path, this.fileService.getByte(path)));
                        }
                    }
                }
            }
            default -> {
            }
        }
    }

    @Override
    public void setHandler(ISend<SimplePackage> handler) {
        this.handler = handler;
    }

}
