/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel.Sql;

import MOdel.Source.Setting;
import Unicast.commons.Actions.Object.MyName;
import com.alibaba.fastjson.JSONObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public ResultSet executeQuery(Connection connection, String sql) {
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
    public boolean execute(Connection connection, String sql) {
        if (connection == null) {
            return false;
        }
        try {
            return connection.createStatement().execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
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
            ResultSet resultSet = executeQuery(conn, String.format("call checkPcName('%s')", pcName));
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
            ResultSet resultSet = executeQuery(conn, String.format("call getPcId('%s')", pcName));
            if (resultSet != null && resultSet.next()) {
                return resultSet.getLong(1);
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<String> getListProduct() {
        try ( Connection conn = getConnection()) {
            List<String> list = new ArrayList<>();
            ResultSet resultSet = executeQuery(conn, "call getListProduct()");
            while (resultSet != null && resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getListStatus(String productName) {
        try ( Connection conn = getConnection()) {
            List<String> list = new ArrayList<>();
            ResultSet resultSet = executeQuery(conn, String.format("call getListStation('%s')", productName));
            while (resultSet != null && resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getListLines(String productName) {
        try ( Connection conn = getConnection()) {
            List<String> list = new ArrayList<>();
            ResultSet resultSet = executeQuery(conn, String.format("call getListLine('%s')", productName));
            while (resultSet != null && resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getListPcOnline(String product, String Station, String line) {
        try ( Connection conn = getConnection()) {
            List<String> list = new ArrayList<>();
            ResultSet resultSet = executeQuery(conn,
                    String.format("call getListPcOnline('%s','%s','%s')",
                            product, Station, line));
            while (resultSet != null && resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean setPcInfomation(long id, MyName myName) {
        try ( Connection conn = getConnection()) {
            return execute(conn,
                    String.format("call setPcInfomation('%s','%s','%s')",
                            id, myName.getOS(), new JSONObject(myName.getdata())));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setPcStatus(long id, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
