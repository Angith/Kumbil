package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class menu {
    @SerializedName("userId")
    int UserId;
    @SerializedName("dishname")
    String DishName;
    @SerializedName("price")
    float Price;
    @SerializedName("description")
    String Description;

    public menu(int UserId, String DishName, float Price, String Description) {
        this.DishName = DishName;
        this.Price = Price;
        this.Description = Description;
        this.UserId = UserId;
    }

    public String getDishName() {
        return DishName;
    }

    public float getPrice() {
        return Price;
    }

    public String getDescription() {
        return Description;
    }

    public int getUserId() {
        return UserId;
    }
}