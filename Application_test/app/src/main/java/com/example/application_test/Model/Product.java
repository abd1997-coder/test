package com.example.application_test.Model;

public class Product {

    String    product_name  ;
    int quantity , product_price ;
    public Product( String product_name, int quantity, int product_price) {
        this.quantity = quantity;
        this.product_name = product_name;
        this.product_price = product_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }
}
