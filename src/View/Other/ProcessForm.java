/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Other;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Administrator
 */
public class ProcessForm extends javax.swing.JFrame {

    /**
     * Creates new form ProcessForm
     *
     * @param max
     * @param title
     */
    public ProcessForm(int max, String title) {
        initComponents();
        if (title != null && title.isBlank()) {
            this.setTitle(String.format("Upload - %s", title));
        }
        this.process.setStringPainted(true);
        this.process.setMaximum(max < 0 ? 0 : max);
    }

    /**
     * Creates new form ProcessForm
     *
     * @param max
     */
    public ProcessForm(int max) {
        this(max, null);
    }

    public void setValueProcessbar(int value) {
        this.lb_num.setText(String.format("%s/%s", value, this.process.getMaximum()));
        this.process.setValue(value < 0 ? 0 : value);
    }

    public void appendLine(String line) {
        this.txtAreaList.append(String.format("%s\r\n", line));
    }

    public boolean isCloseed() {
        return !this.isVisible();
    }

    public void stop() {
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        process = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaList = new javax.swing.JTextArea();
        lb_num = new javax.swing.JLabel();

        setTitle("Upload");

        txtAreaList.setEditable(false);
        txtAreaList.setColumns(10);
        txtAreaList.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtAreaList.setRows(5);
        jScrollPane1.setViewportView(txtAreaList);

        lb_num.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb_num.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_num.setText(". / .");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(process, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addComponent(lb_num, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lb_num)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(process, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    public void display() {
        this.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_num;
    private javax.swing.JProgressBar process;
    private javax.swing.JTextArea txtAreaList;
    // End of variables declaration//GEN-END:variables
}
