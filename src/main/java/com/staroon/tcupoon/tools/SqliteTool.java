package com.staroon.tcupoon.tools;

import com.staroon.tcupoon.model.Urls;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018/7/5
 * Time: 11:29
 * To change this template use File | Settings | File Templates.
 */
public class SqliteTool {

    private static String dbFile = new Commons().getTcupHome() + "/data/tcupoon.tdb";

    private static Connection getDbConn() {
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
        Connection conn = getDbConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO urls(url, orig_path, upload_time) VALUES(" +
                    "'" + urls.getUrl() + "'," +
                    "'" + urls.getOrigPath() + "'," +
                    "'" + urls.getUploadTime() + "')";
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

    public static List<Urls> getUrlsList() {
        List<Urls> urlsList = new ArrayList<Urls>();
        Connection conn = getDbConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM urls ORDER BY id DESC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String idCol = String.valueOf(rs.getInt("id"));
                String urlCol = rs.getString("url");
                String localCol = rs.getString("orig_path");
                String updateCol = rs.getString("upload_time");
                urlsList.add(new Urls(idCol, urlCol, localCol, updateCol));
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return urlsList;
    }

    public static void main(String[] args) {
//        writeToDb(new Urls("http://aaabbbbb", "D:/ddd", "2018-07-05 14:52:23"));
        List<Urls> urls = getUrlsList();
        for (Urls url : urls) {
            System.out.println(url.toString());
        }
    }
}
