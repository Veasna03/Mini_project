package org.example.model.entity;

public class Product {
    private String name;
    private double unit_price;
    private int qty;
    private String import_date;

    public Product(String name, double unit_price, int qty,String import_date) {
        this.name = name;
        this.unit_price = unit_price;
        this.qty = qty;
        this.import_date = import_date;
    }

    public String getImport_date() {
        return import_date;
    }

    public void setImport_date(String import_date) {
        this.import_date = import_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
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
        return "Product{" +
                "name='" + name + '\'' +
                ", unit_price=" + unit_price +
                ", qty=" + qty +
                '}';
    }
}
