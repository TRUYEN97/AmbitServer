/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Other;

import Control.Servants;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Administrator
 */
public class Filter extends javax.swing.JPanel {

    private final Servants servants;
    private final boolean addAllSelect;

    /**
     * Creates new form Filter
     *
     * @param servants
     * @param addAllSelect
     */
    public Filter(Servants servants, boolean addAllSelect) {
        initComponents();
        this.addAllSelect = addAllSelect;
        this.servants = servants;
        setElementsForFilter(this.cbb_product, this.servants.getListProduct());
    }

    public void setEditable(boolean product, boolean station, boolean line) {
        this.cbb_product.setEditable(product);
        this.cbb_station.setEditable(station);
        this.cbb_line.setEditable(line);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbb_product = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbb_station = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbb_line = new javax.swing.JComboBox<>();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        cbb_product.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_productItemStateChanged(evt);
            }
        });
        cbb_product.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbb_productPopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Product");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Station");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Line");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbb_line, 0, 91, Short.MAX_VALUE)
                    .addComponent(cbb_product, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbb_station, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cbb_product, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cbb_station, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cbb_line, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbb_productItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_productItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            List<String> stations = this.servants.getListStation(evt.getItem().toString());
            setElementsForFilter(this.cbb_station, stations);
            List<String> lines = this.servants.getListLine(evt.getItem().toString());
            setElementsForFilter(this.cbb_line, lines);
        }
    }//GEN-LAST:event_cbb_productItemStateChanged

    private void cbb_productPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbb_productPopupMenuWillBecomeVisible
        // TODO add your handling code here:
        setElementsForFilter(this.cbb_product, this.servants.getListProduct());
    }//GEN-LAST:event_cbb_productPopupMenuWillBecomeVisible

    private void setElementsForFilter(JComboBox comboBox, List elems) {
        if (elems == null) {
            return;
        }
        DefaultComboBoxModel<String> boxModel = (DefaultComboBoxModel<String>) comboBox.getModel();
        boxModel.removeAllElements();
        if (addAllSelect) {
            boxModel.addElement("All");
        }
        boxModel.addAll(elems);
        if (elems.isEmpty()) {
            return;
        }
        comboBox.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbb_line;
    private javax.swing.JComboBox<String> cbb_product;
    private javax.swing.JComboBox<String> cbb_station;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables

    public String getProductItem() {
        var obj = this.cbb_product.getSelectedItem();
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public String getStationItem() {
        var obj = this.cbb_station.getSelectedItem();
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public String getLineItem() {
        var obj = this.cbb_line.getSelectedItem();
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

}
