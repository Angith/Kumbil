package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("address")
    String address;
    @SerializedName("cookId")
    int cookId;
    @SerializedName("customerId")
    int customerId;
    @SerializedName("dishId")
    int dishId;
    @SerializedName("status")
    String status;
    @SerializedName("date")
    String date;
    @SerializedName("time")
    String time;
    @SerializedName("quantity")
    int quantity;
    @SerializedName("dishname")
    String DishName;
    @SerializedName("price")
    float Price;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getCookId() {
        return cookId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getDishId() {
        return dishId;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDishName() {
        return DishName;
    }

    public float getPrice() {
        return Price;
    }
}
