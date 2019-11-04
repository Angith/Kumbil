package com.kumbil.neha;

public class Cart {

    String DishName;
    String price;
    String qty;

    public Cart(String DishName, String price, String qty) {
        this.DishName = DishName;
        this.price = price;
        this.qty = qty; }

    public String getDishName() {
        return DishName;
    }

    public String getPrice() {
        return price;
    }

    public String getQty() {
        return qty;
    }

}
