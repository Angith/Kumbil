package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class UpdateOrder {
    @SerializedName("status")
    String status;
    @SerializedName("id")
    int id;

    public UpdateOrder(String status, int id) {
        this.status = status;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }
}
