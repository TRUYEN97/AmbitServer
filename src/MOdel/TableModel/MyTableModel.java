/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel.TableModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class MyTableModel {

    private DefaultTableModel tableModel;
    private final JTable table;
    private final List<Object[]> currRows;

    public MyTableModel(JTable table) {
        super();
        this.table = table;
        this.currRows = new ArrayList();
    }

    public void initTable(Object[] cols) {
        if (cols == null || cols.length == 0) {
            return;
        }
        int columnCount = cols.length;
        int[] sizes;
        boolean[] editable;
        sizes = new int[columnCount];
        for (int i = 0; i < columnCount; i++) {
            sizes[i] = 100 / columnCount;
        }
        editable = new boolean[cols.length];
        for (int i = 0; i < columnCount; i++) {
            editable[i] = false;
        }
        initTable(cols, sizes, editable);
    }

    public void initTable(Object[] cols, int[] size, boolean[] editable) {
        this.table.setModel(
                new javax.swing.table.DefaultTableModel(null, cols) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return editable[columnIndex];
            }
        });
        this.table.getTableHeader().setReorderingAllowed(true);//
        this.table.setShowGrid(true);
        this.tableModel = (DefaultTableModel) this.table.getModel();
        float onePercent = this.table.getWidth() / 100;
        int sizeLenth = size.length;
        for (int i = 0; i < cols.length; i++) {
            int w = (int) (sizeLenth <= i ? (onePercent * size[sizeLenth - 1]) : (onePercent * size[i]));
            setPropertiesColumn(i, w, JLabel.CENTER, JLabel.CENTER);
        }
    }

    public int getSelectedRow() {
        return this.table.getSelectedRow();
    }

    public int getColumnCount() {
        return this.tableModel.getColumnCount();
    }

    public int getSelectedRowCount() {
        return this.table.getSelectedRowCount();
    }

    public int[] getSelectedRows() {
        return this.table.getSelectedRows();
    }

    public void clear() {
        this.tableModel.setRowCount(0);
        this.currRows.clear();
    }

    public void refresh() {
        tableModel.setRowCount(0);
        for (Object[] row : currRows) {
            tableModel.addRow(row);
        }
    }

    public void deleteAll(int[] selectedRows) {
        List rowDelete = new ArrayList();
        for (int selectedRow : selectedRows) {
            rowDelete.add(this.currRows.get(selectedRow));
        }
        this.currRows.removeAll(rowDelete);
        refresh();
    }

    public void delete(int index) {
        if (index < 0 || index >= this.currRows.size()) {
            return;
        }
        this.currRows.remove(index);
        refresh();
    }

    public void addTableRow(Object[] row) {
        if (row == null || row.length == 0) {
            return;
        }
        this.tableModel.addRow(row);
        this.currRows.add(row);
    }

    public int getRowCount() {
        return this.tableModel.getRowCount();
    }

    private void setPropertiesColumn(int index, int width, int alignment, int header) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(alignment);
        this.table.getColumnModel().getColumn(index).setMinWidth(width);
        this.table.getColumnModel().getColumn(index).setCellRenderer(renderer);
        this.table.getColumnModel().getColumn(index).setResizable(true);
        DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
        renderer1.setHorizontalTextPosition(header);
        this.table.getColumnModel().getColumn(index).setHeaderRenderer(renderer);
    }

}
