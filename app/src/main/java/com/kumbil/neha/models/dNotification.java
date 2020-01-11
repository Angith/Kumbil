package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class dNotification {
    @SerializedName("sAddress")
    private String sAddress;
    @SerializedName("dAddress")
    private String dAddress;
    @SerializedName("deliveryTime")
    private String deliveryTime;
    @SerializedName("status")
    private String status;

    public dNotification(String sAddress, String dAddress, String deliveryTime, String status) {
        this.sAddress = sAddress;
        this.dAddress = dAddress;
        this.deliveryTime = deliveryTime;
        this.status = status;
    }

    public String getsAddress() {
        return sAddress;
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
