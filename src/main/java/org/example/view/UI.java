package org.example.view;

import org.example.Stock;
import org.example.controller.ProductController;
import org.example.model.ProductImpl;
import org.example.model.ProductService;
import org.example.model.entity.Product;
import org.example.uitl.DB;
import org.example.validation.Utils;
import org.example.validation.displayTable;
import org.example.validation.inputUtil;

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
            System.out.println(Utils.green+ " W) Write  R) Read      U) Update    D) Delete    S) Search (name)  Se) Set rows\n" +
                    "        Sa) Save  Un) Unsave   Ba) Back Up  Re) Restore  E) Exit");

            String option = inputUtil.option(Utils.blue+"Choose an option: "+Utils.reset);
            switch (option) {
                case "W": {

                  productWrite=  productController.WriteProductt();
                }
                break;
                case "Sa": {
               productController.Save(productWrite);



                }
                break;
                case "R": {

                  productList=  productController.getAllProduct();
                }
                break;
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
                            //update
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




                }
                break;
                case "Ba": {

                }
                break;
                case "D": {
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