package org.example.model;

import org.example.model.entity.Product;

import java.sql.SQLException;
import java.util.List;
public interface ProductService {
    List<Product> write() throws SQLException;
    void Save(List<Product> product ) throws SQLException;
    void Unsave(List<Product> product) throws SQLException;
    List<Product> update(int id) throws SQLException;
    List<Product> getAll() throws SQLException;

}
