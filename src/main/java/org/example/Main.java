package org.example;

import jdk.jshell.execution.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        Utils utils = new Utils();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Utils.Menu();
        try {
            String query  = "select * from stock";
            con = Utils.getConnection();

            st = con.createStatement();
//            st= Utils.createTable(con);
            rs = st.executeQuery(query);
            while (rs.next()){
                System.out.println("id: " + rs.getInt("id") + " name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}