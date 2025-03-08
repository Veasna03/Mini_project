package org.example;

import javax.swing.*;

public class Stock {
    private  int id;
    private String name;
    private int unit_price;
    private int qty ;

    public Stock(String name, int unit_price, int qty) {
        this.name = name;
        this.unit_price = unit_price;
        this.qty = qty;
    }

    public Stock(int id, String name, int unit_price, int qty) {
        this.id = id;
        this.name = name;
        this.unit_price = unit_price;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", unit_price=" + unit_price +
                ", qty=" + qty +
                '}';
    }
}
