package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class postMenu {
    @SerializedName("userId")
    int DishId;
    @SerializedName("dishname")
    String DishName;
    @SerializedName("price")
    float Price;
    @SerializedName("description")
    String Description;

    public postMenu(int dishId, String dishName, float price, String description) {
        DishId = dishId;
        DishName = dishName;
        Price = price;
        Description = description;
    }
}
