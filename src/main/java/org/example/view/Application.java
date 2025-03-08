package org.example.view;

import org.example.uitl.DB;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        UI ui = new UI();
        ui.AllInfo();
    }
}
