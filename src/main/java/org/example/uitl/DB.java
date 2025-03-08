package org.example.uitl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    public static Connection getConnection() {
//        ==================================================================
        String url = "jdbc:postgresql://pg-1395c353-nhanhkimson-904f.i.aivencloud.com:14810/managementsystem";
        String user = "avnadmin";
        String password = "AVNS_IGJGESpMLld9oRRLacZ";
//        ==================================================================
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Statement createTable() throws SQLException {
        Connection connection=DB.getConnection();
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS stock (id serial PRIMARY KEY, name VARCHAR(250), unit_price decimal,qty int,import_date varchar(250))");
            System.out.println("Table created");
            return st;
        }
    }
}
