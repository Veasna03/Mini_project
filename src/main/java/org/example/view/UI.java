package org.example.view;

import org.example.controller.ProductController;
import org.example.helper.Menu;
import org.example.model.entity.Product;
import org.example.uitl.DB;
import org.example.helper.Utils;
import org.example.helper.displayTable;
import org.example.helper.inputUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class UI {
    inputUtil inputUtil=new inputUtil();

    public  void AllInfo() throws SQLException {
        Scanner scanner=new Scanner(System.in);
        List<Product> productList=new ArrayList<>();
        List<Product> productWrite=new ArrayList<>();
        List<Product> productUpdate=new ArrayList<>();
        ProductController productController=new ProductController();
        DB.createTable();
        do {
            Menu menu=new Menu();
            menu.MenuMain();

            String option = inputUtil.option(Utils.blue+"Choose an option: "+Utils.reset);
            switch (option) {
                case "W": {

                  productWrite=  productController.WriteProductt();
                }
                break;
                case "Sa": {
                    System.out.println(  "'si'\t  for saving insert products and 'su'\t for saving update products or 'b' for back to menu");
                    String choose = inputUtil.option(  "=>Choose an option: " );
                    switch (choose) {
                        case "si": {
                            //insert
                            productController.Save(productWrite,choose);
                            break;
                        }
                        case "su": {
                           productController.Save(productUpdate,choose);
                        }
                        break;

                        case "b": {
                            System.out.println(  "Out case unsave..." );
                            return;
                        }
                        default: {
                            System.out.println( "Invalid Input..." );
                        }

                    }

                }
                break;
                case "R": {
                    int id =inputUtil.qty("Enter product id: ");
                   productController.ReadBYID(id);

                }
                break;
                case "Ra":{
                    productList=  productController.getAllProduct();
                }break;
                case "Un": {
                    System.out.println(  "'ui'\t  for saving insert products and 'uu'\t for saving update products or 'b' for back to menu");
                    String choose = inputUtil.option(  "=>Choose an option: " );
                    switch (choose) {
                        case "ui": {
                            //insert
                            displayTable.displaytTable( productWrite);
                            break;
                        }
                        case "uu": {
                            displayTable.displaytTable(productUpdate);
                        }
                        break;

                        case "b": {
                            System.out.println(  "Out case unsave..." );
                            return;
                        }
                        default: {
                            System.out.println( "Invalid Input..." );
                        }

                    }


                }
                break;
                case "U": {

                    int id=inputUtil.qty("Enter product id: ");

                  productUpdate=  productController.UpdateProduct(id);
                }
                break;
                case "Ba": {

                }
                break;
                case "D": {
                    System.out.print("Enter product id to delete: ");
                    int id = scanner.nextInt();
                    productController.DeleteProduct(id);
                }
                break;
                case "Re": {
                }
                break;
                case "S": {

                }
                break;
                case "Se": {

                }
                break;
                case "E": {
                    System.exit(0);
                }
                break;
            }
        }while (true);
}
public  Product WriteDate(){
        Scanner scanner=new Scanner(System.in);
    System.out.println("Input name:");String name=scanner.next();
    System.out.println("Input unit price :");int unitPrice=scanner.nextInt();
    System.out.println("Input qty:");int qty=scanner.nextInt();
    System.out.println("Input import date: ");String date=scanner.next();
    Product product=new Product(name,unitPrice,qty,date);
    return product;
}

}