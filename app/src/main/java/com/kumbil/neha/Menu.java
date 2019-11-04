package com.kumbil.neha;

public class Menu {

    String dishName;
    String price;
    String description;

    public Menu(String dishName, String price, String description) {
        this.dishName = dishName;
        this.price = price;
        this.description = description;
    }

    public String getDishName() {
        return dishName;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
