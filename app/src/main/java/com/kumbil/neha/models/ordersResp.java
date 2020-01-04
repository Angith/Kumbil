package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class ordersResp {
    @SerializedName("status")
    int status;
    @SerializedName("message")
    String message;
    @SerializedName("orders")
    Order ordrs[];

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Order[] getOrdrs() {
        return ordrs;
    }
}
