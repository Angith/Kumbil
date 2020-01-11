package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class Delivery {
    @SerializedName("userId")
    private int userId;
    @SerializedName("dAddress")
    private String dAddress;
    @SerializedName("deliveryTime")
    private String deliveryTime;
    @SerializedName("status")
    private String status;

    public Delivery(int userId, String dAddress, String deliveryTime, String status) {
        this.userId = userId;
        this.dAddress = dAddress;
        this.deliveryTime = deliveryTime;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public String getdAddress() {
        return dAddress;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getStatus() {
        return status;
    }
}
