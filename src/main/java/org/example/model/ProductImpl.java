package org.example.model;

import org.example.model.entity.Product;
import org.example.uitl.DB;
import org.example.validation.inputUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class ProductImpl implements ProductService {
    List<Product> productWrite = new ArrayList<>();
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

//    public List<Product> Save(List<Product> product) throws SQLException {
//
//        Connection con= DB.getConnection();
//        Statement st=con.createStatement();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(" sa.save insert and su.Save Update)");
//        scanner.nextLine();
//        System.out.println("Choose option :");String option=scanner.nextLine();
//        switch (option) {
//            case "si":{
//                PreparedStatement pt=con.prepareStatement("insert into stock values(?,?,?,?)");
//                Iterator<Product> iterator = product.iterator(); // Use Iterator
//                while (iterator.hasNext()) {
//                    Product product1 = iterator.next();
//                    pt.setString(1, product1.getName());
//                    pt.setInt(2,product1.getUnit_price());
//                    pt.setInt(3,product1.getQty());
//                    pt.setString(4,product1.getImport_date());
//                    pt.executeUpdate();
//                    System.out.println("Insert Success");
//                    System.out.println();
//
//                    iterator.remove(); // Safe way to remove elements
//                }
//
//            }break;
//            case "su":{
//                PreparedStatement pt=con.prepareStatement("insert into stock values(?,?,?,?)");
//                Iterator<Product> iterator = product.iterator();
//                while (iterator.hasNext()) {
//                    Product product1 = iterator.next();
//                    pt.setString(1, product1.getName());
//                    pt.setDouble(2,product1.getUnit_price());
//                    pt.setInt(3,product1.getQty());
//                    pt.setString(4,product1.getImport_date());
//                    pt.executeUpdate();
//                    iterator.remove();
//                }
//               break;
//            }
//        }
//        return product;
//    }
    @Override
    public void Save(List<Product> product) throws SQLException {
        Connection con = DB.getConnection();
        con.setAutoCommit(false); // Disable auto-commit

        Scanner scanner = new Scanner(System.in);
        System.out.println("sa: Save Insert | su: Save Update");
        System.out.println("Choose option: ");
        String option = scanner.next(); // Use next() instead of nextLine()
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
                String updateSQL = "UPDATE stock SET name=?, unit_price=?, qty=?, import_date=? WHERE id=?";
                try (PreparedStatement pt = con.prepareStatement(updateSQL)) {
                    for (Product product1 : product) {
                        System.out.println("Enter Product ID to update:");
                        int id = scanner.nextInt();
                        pt.setString(1, product1.getName());
                        pt.setDouble(2, product1.getUnit_price());
                        pt.setInt(3, product1.getQty());
                        pt.setString(4, product1.getImport_date());
                        pt.setInt(5, id);
                        pt.executeUpdate();
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
        Connection con=DB.getConnection();
        Scanner scanner = new Scanner(System.in);
             List<Product> products = new ArrayList<>();
           Statement st=con.createStatement();
           ResultSet rs=st.executeQuery("SELECT * FROM stock WHERE id=?");
           while (rs.next()) {
               if(rs.getInt("id")==id) {
                   System.out.println("1.Name 2.Unit_price 3.qty 4.import_date");
                   System.out.println("Choose option :");int option=scanner.nextInt();
                   switch (option) {
                       case 1:{
                           scanner.next();
                           System.out.println("Change name to:  ");String name=scanner.nextLine();
                           int unit_price=rs.getInt(3);
                           int qty=rs.getInt(4);
                           String import_date= rs.getString(5);
                           products.add(new Product(name,unit_price,qty,import_date));
                       }break;
                       case 2:{
                           System.out.println("Change Unit Price  to:  ");int unit_price=scanner.nextInt();
                           String name=rs.getString(2);
                           int qty=rs.getInt(4);
                           String import_date=rs.getString(5);
                           products.add(new Product(name,unit_price,qty,import_date));
                       }break;
                       case 3:{
                           System.out.println("Change Qty to:  ");int qty=scanner.nextInt();
                           String name=rs.getString(2);
                           int unit_price=rs.getInt(3);
                           String import_date=rs.getString(5);
                           products.add(new Product(name,unit_price,qty,import_date));
                       }
                   }
               }
           }
           return products;
    }


    @Override
    public void delete(int id) throws SQLException {
        Connection con=DB.getConnection();
        String deleteSQL = "DELETE FROM stock WHERE id=?";
        try(PreparedStatement pt = con.prepareStatement(deleteSQL)) {
            pt.setInt(1, id);
            int result =  pt.executeUpdate();
            if (result == 1) {
                System.out.println("Delete Success");
            }else {
                System.out.println("Delete Fail");
            }

        }
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

          products.add(new Product(name,unit_price,qty,import_date));

      }
      return products;

    }
}
