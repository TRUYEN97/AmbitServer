/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel.Sql;

import MOdel.Source.Setting;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class SqlExecute {

    private final Setting setting;

    public SqlExecute(Setting setting) {
        this.setting = setting;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(this.setting.getDatabase(),
                    this.setting.getSqlUser(), this.setting.getSqlPass());
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet execute(Connection connection, String sql) {
        if (connection == null) {
            return null;
        }
        try {
            return connection.createStatement().executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void disConnect(Connection connection) {
        try (connection) {
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkPcName(String pcName) {
        try ( Connection conn = getConnection()) {
            ResultSet resultSet = execute(conn, String.format("call checkPcName('%s')", pcName));
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public long getIdOfPc(String pcName) {
        try ( Connection conn = getConnection()) {
            ResultSet resultSet = execute(conn, String.format("call getPcId('%s')", pcName));
            if (resultSet != null && resultSet.next()) {
                return resultSet.getLong(1);
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
