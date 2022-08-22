/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class Servicer {
    private final Setting setting;

    public Servicer(Setting setting) {
        this.setting = setting;
    }
    
    public List<String> getListPN(String pcName)
    {
        return setting.getPNs(pcName);
    }

    public boolean isHavePN(String pcName, String PN) {
        return setting.pcIsContain(pcName, PN);
    }

    public List<String> getListFile(String pcName, String PN) {
        return setting.getPathFile(pcName, PN);
    }
}
