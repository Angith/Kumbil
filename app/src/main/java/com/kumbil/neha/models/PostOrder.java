package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class PostOrder {
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

    public PostOrder(int cookId, int customerId, int dishId, String status, String date, String time, int quantity) {
        this.cookId = cookId;
        this.customerId = customerId;
        this.dishId = dishId;
        this.status = status;
        this.date = date;
        this.time = time;
        this.quantity = quantity;
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

}
