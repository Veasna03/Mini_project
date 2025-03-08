package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static java.lang.Class.*;

public class Utils {
    static int idUpdate=0;
    static Scanner sc = new Scanner(System.in);
    static String option = null;
     static     List<Stock> stocks = new ArrayList<Stock>();
    public static Connection getConnection() {
//        ==================================================================
        String url ="jdbc:postgresql://pg-1395c353-nhanhkimson-904f.i.aivencloud.com:14810/managementsystem";
        String user = "avnadmin";
        String password = "AVNS_IGJGESpMLld9oRRLacZ";
//        ==================================================================
        try{
            forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to PostgreSQL database");
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void Menu() throws SQLException {
        do {
            SubMenu();
            System.out.print("Choose an option: ");String option = sc.next();
            switch (option){
                case "W":{
                    InsertStock();
                    for(Stock stock : stocks){
                        System.out.println(stock.toString());
                    }
                }break;
                case "Sa":{
                     save(getConnection());
                }break;
                case "R":{
                    for(Stock stock : stocks){
                        System.out.println(stock.toString());
                    }
                }break;
                case "Un":{
                    System.out.println("Kok lola");
                }break;
                case "U":{
                      UpdateStock(getConnection());
                }break;
                case "Ba":{
                    System.out.println("Kok lola");
                }break;
                case "D":{
                    DdispayStock(getConnection());
                }break;
                case "Re":{

                }break;
                case "S":{

                }break;
                case "Se":{

                }break;
                case "E":{
                    System.exit(0);
                }break;
            }

        }while(true);
    }

    public static void SubMenu(){
        System.out.println("""
                 
                 
                 W) Write  R) Read      U) Update    D) Delete    S) Search (name)  Se) Set rows
                 Sa) Save  Un) Unsave   Ba) Back Up  Re) Restore  E) Exit
                 
                                            ------------------------------
                """);
    }
    public static Statement createTable(Connection connection) throws SQLException {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS stock (id serial PRIMARY KEY, name VARCHAR(250), unit_price INT,qty int)");
            return st;
        }
    }
    public  static void InsertStock(){
        sc.nextLine();
        System.out.println("Input name: ");String name= sc.nextLine();
        System.out.println("Input unit_price: ");int unitPrice= sc.nextInt();
        System.out.println("Input qty: ");int qty= sc.nextInt();
        stocks.add(new Stock(name,unitPrice,qty));
    }
    public static void save(Connection con) throws SQLException {
        System.out.println("save insert stock");
        System.out.println("save update stock");
        System.out.println("Choose option: ");
        String option = sc.next();
        switch (option) {
            case "si": {
                if (stocks.isEmpty()) {
                    System.out.println("No stocks to save.");
                    return;
                }
                String insertSQL = "INSERT INTO stock (name, unit_price, qty) VALUES (?, ?, ?)";
                try (PreparedStatement pt = con.prepareStatement(insertSQL)) {
                    Iterator<Stock> iterator = stocks.iterator(); // Use Iterator
                    while (iterator.hasNext()) {
                        Stock stock = iterator.next();
                        pt.setString(1, stock.getName());
                        pt.setInt(2, stock.getUnit_price());
                        pt.setInt(3, stock.getQty());
                        pt.executeUpdate();

                        iterator.remove(); // Safe way to remove elements
                    }
                    System.out.println("Stocks saved successfully!");
                } catch (SQLException e) {
                    try {
                        con.rollback(); // Rollback if an error occurs
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    throw new RuntimeException(e);
                }
            }
            break;
            case "su": {
                Statement st = con.createStatement();
                st.executeQuery("SELECT * FROM stock");

                if(stocks.isEmpty()){
                    System.out.println("No stocks to save.");
                }
                else{

                    PreparedStatement pt=con.prepareStatement("UPDATE stock SET name=? WHERE id=?");

                    for(Stock stock : stocks){
                        pt.setString(1, stock.getName());
                        pt.setInt(2,idUpdate);
                        pt.executeUpdate();
                        System.out.println("Stock updated successfully!");
                    }
                }
            }
            break;
        }
    }
    public  static void UpdateStock(Connection con) throws SQLException {
        System.out.println("Input Id to update :");int idtoupdate= sc.nextInt();
       Statement st= con.createStatement();
       ResultSet rs = st.executeQuery("SELECT * from stock");
       while (rs.next()) {
           int id = rs.getInt("id");
           if(id==idtoupdate){
               idUpdate=idtoupdate;
               System.out.println("1.Name 2.Unit Price 3.Qty");
               System.out.println("Choose option: ");int option= sc.nextInt();
               if(option==1){
                   int price=rs.getInt(3);
                   int qty=rs.getInt(4);
                   sc.nextLine();
                   System.out.println("Change name to:");String name=sc.nextLine();
                   stocks.add(new Stock(name,price,qty));
               }

           }
       }
    }
    public  static void DdispayStock(Connection con) throws SQLException {
        Statement st= con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from stock order by id asc ");
        while (rs.next()) {
                  String name=rs.getString("name");
                   int id=rs.getInt("id");
                   int unit_price= rs.getInt(3);
                   int qty= rs.getInt(4);
                   System.out.println("Id:"+id  +"   Name:"+name  +"   Unit_price:"+unit_price  +"   Qty:"+qty);
//                stocks.add(new Stock(name,unit_price,qty));

        }
    }

}
