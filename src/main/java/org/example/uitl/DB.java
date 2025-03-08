package org.example.uitl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    public static Connection getConnection() {
//        ==================================================================
        String url = "jdbc:postgresql://localhost:5432/Student";
        String user = "sna";
        String password = "sna123";
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
