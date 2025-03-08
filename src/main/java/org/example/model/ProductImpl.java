package org.example.model;

import org.example.model.entity.Product;
import org.example.uitl.DB;
import org.example.helper.displayTable;
import org.example.helper.inputUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class ProductImpl implements ProductService {
    List<Product> productWrite = new ArrayList<>();
    public static int idDatabase=0;
    @Override
    public List<Product>  write() {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd ");//dd/MM/yy
        Date now = new Date();
        String Date= sdfDate.format(now);
        Scanner scanner = new Scanner(System.in);
        inputUtil input =new inputUtil();
        String name=input.Inputname("Enter your name: ");
        double unitPrice=input.inputPrice("Enter your unit price: ");
        int quantity=input.qty("Enter your quantity: ");
        System.out.println("Success...");
        List<Product> products=new ArrayList<>();
        products.add(new Product(name,unitPrice,quantity,Date));
        return products;
    }

    @Override
    public void Save(List<Product> product,String option) throws SQLException {
        Connection con = DB.getConnection();
        con.setAutoCommit(false); // Disable auto-commit

        Scanner scanner = new Scanner(System.in);

        switch (option) {
            case "si": {
                String insertSQL = "INSERT INTO stock(name, unit_price, qty, import_date) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pt = con.prepareStatement(insertSQL)) {
                    for (Product product1 : product) {
                        pt.setString(1, product1.getName());
                        pt.setDouble(2, product1.getUnit_price());
                        pt.setInt(3, product1.getQty());
                        pt.setString(4, product1.getImport_date());
                        pt.executeUpdate();
                        System.out.println("Insert Success");
                    }
                    con.commit(); // Commit transaction
                    product.clear(); // Remove all products after successful save
                } catch (SQLException e) {
                    con.rollback(); // Rollback if an error occurs
                    throw new RuntimeException(e);
                }
            }
            break;

            case "su": {
                System.out.println(idDatabase);
                String updateSQL = "UPDATE stock SET name=?, unit_price=?, qty=?, import_date=? WHERE id=?";
                try (PreparedStatement pt = con.prepareStatement(updateSQL)) {
                    for (Product product1 : product) {
                        pt.setString(1, product1.getName());
                        pt.setDouble(2, product1.getUnit_price());
                        pt.setInt(3, product1.getQty());
                        pt.setString(4, product1.getImport_date());
                        pt.setInt(5, idDatabase);
                        pt.executeUpdate();
                        System.out.println("Update Success");
                    }
                    con.commit();
                    product.clear();
                } catch (SQLException e) {
                    con.rollback();
                    throw new RuntimeException(e);
                }
            }
            break;
        }

    }
    @Override
    public void Unsave(List<Product> product) throws SQLException {
        Connection con=DB.getConnection();
        Scanner scanner = new Scanner(System.in);
        System.out.println("ui : Unsave Insert and uu:Unsave Update");
        System.out.println("Choose option :");String option=scanner.next();
        switch (option) {
            case "ui":{
                for(Product product1 : product) {
                    System.out.println(product1.toString());
                }
            }break;
            case "uu":{
            }
        }
    }


    @Override
    public List<Product> update(int id) throws SQLException {
        inputUtil input =new inputUtil();
        Connection con=DB.getConnection();
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
           Statement st=con.createStatement();
           ResultSet rs=st.executeQuery("SELECT * FROM stock ");
           while (rs.next()) {
               int idDB = rs.getInt("id");
               if(idDB==id) {
                   idDatabase=idDB;

                   System.out.println("1.Name 2.Unit_price 3.qty 4.import_date");
                 int option=input.qty("Choose option :");
                   switch (option) {
                       case 1:{
                            String newName = input.Inputname("Enter your name: ");
                           double unit_price=rs.getInt(3);
                           int qty=rs.getInt(4);
                           String import_date= rs.getString(5);
                           products.add(new Product(newName,unit_price,qty,import_date));
                       }break;
                       case 2:{
                           double unit_price=input.inputPrice("Change Unit Price  to:  ");
                           String name=rs.getString(2);
                           int qty=rs.getInt(4);
                           String import_date=rs.getString(5);
                           products.add(new Product(name,unit_price,qty,import_date));
                       }break;
                       case 3:{
                           int qty=input.qty("Change Qty to:  ");
                           String name=rs.getString(2);
                           int unit_price=rs.getInt(3);
                           String import_date=rs.getString(5);
                           products.add(new Product(name,unit_price,qty,import_date));
                       }
                       default:{
                           System.out.println("Invalid option");
                       }

                   }

               }
           }
        System.out.println("Update Success");
           return products;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> products = new ArrayList<>();
       Connection con= DB.getConnection();
      Statement st=  con.createStatement();
      ResultSet rs=st.executeQuery("SELECT * FROM stock");
      while (rs.next()) {
          int id=rs.getInt(1);
          String name=rs.getString(2);
          int unit_price=rs.getInt(3);
          int qty=rs.getInt(4);
          String import_date=rs.getString(5);
          System.out.println("Id:  "+id + "   Name:  " + name + "   Unit_price:  " + unit_price+"   Import date :"+import_date);

          products.add(new Product(id,name,unit_price,qty,import_date));

      }
      return products;

    }

    @Override
    public void readById(int id) throws SQLException {
        List<Product> productById = new ArrayList<>();
        Connection con = DB.getConnection();
        String searchByIdSql = "SELECT * FROM stock WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(searchByIdSql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int pid = rs.getInt(1);
            String name = rs.getString(2);
            double unit_price = rs.getDouble(3);
            int qty = rs.getInt(4);
            String import_date = rs.getString(5);
            productById.add(new Product(pid, name, unit_price, qty, import_date));

        }

        displayTable.displaytTable(productById);

    }
    @Override
    public void readByName(String name) throws SQLException {
        List<Product> productByName = new ArrayList<>();
        Connection con = DB.getConnection();
        String searchByIdSql = "SELECT * FROM stock WHERE name = ?";
        PreparedStatement pstmt = con.prepareStatement(searchByIdSql);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int pid = rs.getInt(1);
            String namee = rs.getString(2);
            double unit_price = rs.getDouble(3);
            int qty = rs.getInt(4);
            String import_date = rs.getString(5);
            productByName.add(new Product(pid, namee, unit_price, qty, import_date));

        }

        displayTable.displaytTable(productByName);

    }





}
