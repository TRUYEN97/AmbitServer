/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel.TableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Administrator
 * @param <T>
 */
public class MyTableListModel<T> extends MyTableModel {

    private final List<T> arr;

    public MyTableListModel(JTable table) {
        super(table);
        this.arr = new ArrayList<>();
    }

    @Override
    public void clear() {
        super.clear();
        this.arr.clear();
    }

    @Override
    public void delete(int index) {
        super.delete(index);
        this.arr.remove(index);
    }

    public void addRow(List elens, T elem) {
        if (elem == null || elens == null) {
            return;
        }
        Object[] row = new Object[elens.size()];
        for (int i = 0; i < elens.size(); i++) {
            row[i] = elens.get(i);
        }
        addTableRow(row);
        this.arr.add(elem);
    }

    public List<T> getValues() {
        return new ArrayList<>(this.arr);
    }

    public T getValue(int i) {
        return i >= 0 && i < this.arr.size() ? this.arr.get(i) : null;
    }

    public T getValueHasSelected() {
        if (getSelectedRowCount() <= 0) {
            return null;
        }
        return this.arr.get(getSelectedRow());
    }

    @Override
    public void deleteAll(int[] selectedRows) {
        super.deleteAll(selectedRows); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        for (int selectedRow : selectedRows) {
            this.arr.remove(selectedRow);
        }
    }
    

}
