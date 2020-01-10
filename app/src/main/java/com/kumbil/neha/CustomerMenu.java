package com.kumbil.neha;

public class CustomerMenu {

    int id;
    String DishName;
    float price;
    String Description;

    public CustomerMenu(int id, String DishName, float price, String Description) {
        this.id = id;
        this.DishName = DishName;
        this.price = price;
        this.Description = Description; }

    public int getId() {
        return id;
    }

    public String getDishName() {
        return DishName;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription(){return Description;}

}
