/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Program;

import Control.Servants;
import MOdel.TableModel.MyTableListModel;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ShowProject extends javax.swing.JPanel {

    private final Servants servants;
    private final MyTableListModel<String> projectTableModel;
    private final ShowVersion showVersion;
    /**
     * Creates new form showProject
     * @param servants
     */
    public ShowProject( Servants servants) {
        initComponents();
        this.projectTableModel = new MyTableListModel<>(this.tb_Projects);
        this.servants = servants;
        this.showVersion = new ShowVersion(servants);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Projects = new javax.swing.JTable();

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("List project"));

        tb_Projects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_Projects.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_ProjectsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Projects);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tb_ProjectsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ProjectsMouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON3) {

        } else if (evt.getClickCount() > 1) {
            getAllVersionOf(this.projectTableModel.getValueHasSelected());
        }
    }//GEN-LAST:event_tb_ProjectsMouseClicked

    private void getAllVersionOf(String project) {
        if (project == null) {
            return;
        }
        this.showVersion.show(project);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_Projects;
    // End of variables declaration//GEN-END:variables
}
