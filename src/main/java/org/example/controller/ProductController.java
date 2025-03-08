package org.example.controller;

import org.example.model.ProductImpl;
import org.example.model.ProductService;
import org.example.model.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    ProductService productService= new ProductImpl();



    public List<Product> WriteProductt() throws SQLException {
        return productService.write();
    }
    public List<Product> getAllProduct() throws SQLException {
        return productService.getAll();
    }
    public void Save(List<Product> products,String option) throws SQLException {
           productService.Save(products,option);
    }
    public List<Product> UpdateProduct(int id) throws SQLException {
        return productService.update(id);
    }
    public void ReadBYID(int id) throws SQLException {
        productService.readById(id);
    }
}
