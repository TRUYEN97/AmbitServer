/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel.Sql;

import MOdel.Source.Setting;
import Unicast.commons.Actions.MapRowsParameter;
import Unicast.commons.Actions.Object.MyName;
import Unicast.commons.Actions.TableListRowParameter;
import com.alibaba.fastjson.JSONObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class SqlExecute {

    private final Setting setting;
    public static final int ON = 1;
    public static final int OFF = 0;

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

    public ResultSet executeQuery(Connection connection, String sql) throws SQLException {
        if (connection == null) {
            return null;
        }
        return connection.createStatement().executeQuery(sql);
    }

    public boolean execute(Connection connection, String sql) throws SQLException {
        if (connection == null) {
            return false;
        }
        return connection.createStatement().execute(sql);
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

    public TableListRowParameter getListPc(String product, String Station, String line) {
        try ( Connection conn = getConnection()) {
            TableListRowParameter list = new TableListRowParameter();
            ResultSet resultSet = executeQuery(conn,
                    String.format("call getListPc('%s','%s','%s')",
                            product, Station, line));
            if (resultSet != null) {
                return getTableParamater(resultSet);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean setPcStatus(String id, int stt) {
        try ( Connection conn = getConnection()) {
            return execute(conn,
                    String.format("call setPcStatus('%s','%s')",
                            id, stt));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isPcNameExistsInServer(String pcName) {
        try ( Connection conn = getConnection()) {
            ResultSet resultSet = executeQuery(conn,
                    String.format("call isPcNameExists('%s')", pcName));
            return resultSet != null && resultSet.next() && resultSet.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePcInfo(MyName myName, String hostAddress) {
        try ( Connection conn = getConnection()) {
            return execute(conn,
                    String.format("call setpcinfomation('%s','%s','%s','%s')",
                            myName.getPcName(), myName.getOS(), hostAddress, new JSONObject(myName.getdata())));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String addNewPc(String pcNumble, String product, String station, String line) {
        try ( Connection conn = getConnection()) {
            execute(conn,
                    String.format("call addNewPc('%s-%s','%s','%s','%s')",
                            station, pcNumble, product, station, line));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public TableListRowParameter getProject(String projectName) throws SQLException {
        try ( Connection conn = getConnection()) {
            TableListRowParameter programs = new TableListRowParameter();
            ResultSet resultSet = executeQuery(conn,
                    String.format("call getProject('%s')", projectName));
            if (resultSet != null) {
                return getTableParamater(resultSet);
            }
            return programs;
        }
    }

    public void addVersionProram(int userID, String projectName, String programName,
            String version, String detail, String programMd5, String sourceMD5,
            String programFile, String sourceFile) throws SQLException {
        try ( Connection conn = getConnection()) {
            execute(conn,
                    String.format("call addVersionProram('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s' , '%s')",
                            userID, projectName, programName,
                            version, detail, programMd5, sourceMD5, programFile, sourceFile));
        }
    }

    public void addVersionConfig(int userID, String projectName, String configName, String version, String description, int type, String Md5, String configFile) throws SQLException {
        try ( Connection conn = getConnection()) {
            execute(conn,
                    String.format("call addVersionConfig('%s', '%s', '%s', '%s', '%s', %s, '%s', '%s')",
                            userID, projectName, configName, version, description, type, Md5, configFile));
        }
    }

    public void addNewProject(String projectName, int userID, String programFolder) throws SQLException {
        try ( Connection conn = getConnection()) {
            execute(conn,
                    String.format("call addnewProject('%s', '%s', '%s')",
                            projectName, userID, programFolder));
        }
    }

    public void removeVersionProgram(String project, String programtName, String version) throws SQLException {
        try ( Connection conn = getConnection()) {
            execute(conn,
                    String.format("call removeVersionProgram('%s', '%s', '%s')",
                            project, programtName, version));
        }
    }

    public void mappingProjectWithPC(String pcName, String project, String program, Object defaultFolder, Object folder) throws SQLException {
        try ( Connection conn = getConnection()) {
            execute(conn,
                    String.format("call mappingProjectWithPC('%s', '%s', '%s', '%s', '%s')",
                            pcName, project, program,
                            defaultFolder, folder == null ? defaultFolder : folder));
        }
    }

    public TableListRowParameter findAllVersions(String project) throws SQLException {
        try ( Connection conn = getConnection()) {
            ResultSet resultSet = executeQuery(conn,
                    String.format("call findAllVersions('%s')", project));
            if (resultSet != null) {
                return getTableParamater(resultSet);
            }
            return null;
        }
    }

    public String getProgramNameOfProject(String project) throws SQLException {
        try ( Connection conn = getConnection()) {
            ResultSet resultSet = executeQuery(conn,
                    String.format("call getProgramNameOfProject('%s')", project));
            if (resultSet != null && resultSet.next()) {
                return resultSet.getString(1);
            }
            return null;
        }
    }

    public MapRowsParameter getPackageProgram(String pcName , MapRowsParameter tableParam) throws SQLException {
        return getPackageProgram(pcName, "all", tableParam);
    }

    public MapRowsParameter getPackageProgram(String pcName, String project, MapRowsParameter tableParam) throws SQLException {
        try ( Connection conn = getConnection()) {
            ResultSet resultSet = executeQuery(conn,
                    String.format("call getPackageProgram('%s', '%s')", pcName, project));
            if (resultSet != null) {
                return getMapParamater(resultSet, tableParam);
            }
            return null;
        }
    }

    public TableListRowParameter getListConfigOfProject(int type, String project) throws SQLException {
        try ( Connection conn = getConnection()) {
            ResultSet resultSet = executeQuery(conn,
                    String.format("call getListConfigOfProject(%s, '%s')", type, project));
            if (resultSet != null) {
                return getListParamater(resultSet);
            }
            return null;
        }
    }

    public TableListRowParameter getListPcMappingProject(boolean hasMapping, String projectName, String productItem, String stationItem, String lineItem) throws SQLException {
        try ( Connection conn = getConnection()) {
            ResultSet resultSet = executeQuery(conn,
                    String.format("call getListPcMappingProject(%s,'%s','%s', '%s', '%s')",
                            hasMapping ? 1 : 0, projectName, productItem, stationItem, lineItem));
            if (resultSet != null) {
                return getListParamater(resultSet);
            }
            return null;
        }
    }

    private TableListRowParameter getTableParamater(ResultSet resultSet) throws SQLException {
        TableListRowParameter tableParam = new TableListRowParameter();
        var metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            tableParam.addColumn(metaData.getColumnLabel(i));
        }
        tableParam.setDataRows(getDataset(resultSet));
        return tableParam;
    }
    
    private TableListRowParameter getTableParamater(ResultSet resultSet, TableListRowParameter tableParam) throws SQLException {
        var metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            tableParam.addColumn(metaData.getColumnLabel(i));
        }
        tableParam.setDataRows(getDataset(resultSet));
        return tableParam;
    }

    private MapRowsParameter getMapParamater(ResultSet resultSet, MapRowsParameter tableParam) throws SQLException {
        var metaData = resultSet.getMetaData();
        String[] keys = new String[metaData.getColumnCount()];
        for (int i = 1; i <= keys.length; i++) {
            keys[i - 1] = metaData.getColumnLabel(i);
        }
        HashMap<String, String> row;
        while (resultSet.next()) {
            row = new HashMap<>();
            for (int i = 1; i <= keys.length; i++) {
                row.put(keys[i - 1], resultSet.getString(i));
            }
            tableParam.addRow(row);
        }
        return tableParam;
    }

    private List<List<String>> getDataset(ResultSet resultSet) throws SQLException {
        List<List<String>> rows = new ArrayList<>();
        if (resultSet == null) {
            return rows;
        }
        var metaData = resultSet.getMetaData();
        while (resultSet.next()) {
            int colSize = metaData.getColumnCount();
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= colSize; i++) {
                row.add(resultSet.getString(i));
            }
            rows.add(row);
        }
        return rows;
    }

    private TableListRowParameter getListParamater(ResultSet resultSet) throws SQLException {
        TableListRowParameter tableParam = new TableListRowParameter();
        var metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            tableParam.addColumn(metaData.getColumnLabel(i));
        }
        while (resultSet.next()) {
            if (metaData.getColumnCount() > 0) {
                tableParam.addElem(resultSet.getString(1));
            }
        }
        return tableParam;
    }

}
