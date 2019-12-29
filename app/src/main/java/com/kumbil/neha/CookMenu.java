package com.kumbil.neha;

public class CookMenu {

    String dishName;
    float price;
    String description;

    public CookMenu(String dishName, float price, String description) {
        this.dishName = dishName;
        this.price = price;
        this.description = description;
    }

    public String getDishName() {
        return dishName;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
