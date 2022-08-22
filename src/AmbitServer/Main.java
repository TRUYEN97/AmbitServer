/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AmbitServer;

import Control.Core;
import MOdel.Setting;
import View.Display;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class Main {
    public static void main(String[] args) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
        //</editor-fold>
        
        Display display = new Display();
        Setting setting = new Setting("Setting/Setting.json");
        try {
            Core core = new Core(display, setting);
            display.setCore(core);
            display.setVersion(setting.getVersion());
            core.run();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
