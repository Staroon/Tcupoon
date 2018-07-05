package com.staroon.tcupoon.common;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018/7/5
 * Time: 11:29
 * To change this template use File | Settings | File Templates.
 */
public class SqliteTool {

    String dbPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
    String dbFile = dbPath + "/../data/tcupoon.tdb";

    public Connection getDbConn() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return conn;
    }

    public static void writeToDb(Urls urls) {
        Connection conn = new SqliteTool().getDbConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO urls(url, orig_path, upload_time) VALUES(" +
                    "'" + urls.getUrl() + "'," +
                    "'" + urls.getOrig_path() + "'," +
                    "'" + urls.getUpload_time() + "')";
            int result = stmt.executeUpdate(sql);

            if (result == 0) {
                System.out.println("Insert failed");
            }
            stmt.close();
            System.out.println("Insert success");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        writeToDb(new Urls("http://aaa","D:/ddd","2018-07-05 14:52:23"));
    }
}
