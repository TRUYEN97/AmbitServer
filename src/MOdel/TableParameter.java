/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class TableParameter {

    private final List<String> column;
    private final List<List<String>> rowData;
    private final List<String> rowElem;

    public TableParameter() {
        this.column = new ArrayList<>();
        this.rowData = new ArrayList<>();
        this.rowElem = new ArrayList<>();
    }

    public void addColumn(String columnName) {
        this.column.add(columnName);
    }

    public void addElem(String row) {
        this.rowElem.add(row);
    }

    public void addRow(List<String> row) {
        this.rowData.add(row);
    }

    public void resetColumn() {
        this.column.clear();
    }

    public void resetRow() {
        this.rowData.clear();
    }

    public Object[] getObjectColumn() {
        return this.column.toArray();
    }

    public String getElem(int index) {
        if (index < 0 || rowElem.size() <= index) {
            return null;
        }
        return rowElem.get(index);
    }

    public List<String> getRowElem() {
        return new ArrayList<>(rowElem);
    }

    public List<String> getListColumn() {
        return new ArrayList<>(this.column);
    }

    public void remove(int index) {
        this.rowData.remove(index);
    }

    public List<List<String>> getAll() {
        return new ArrayList<>(this.rowData);
    }

    public int getColmunCount() {
        return this.column.size();
    }

    public void setDataRows(List<List<String>> dataset) {
        this.rowData.clear();
        this.rowData.addAll(dataset);
    }

}
