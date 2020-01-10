package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class searchResp {
    @SerializedName("status")
    int status;
    @SerializedName("message")
    String message;
    @SerializedName("menu")
    Cook cooks[];

    public searchResp(int status, String message, Cook[] cooks) {
        this.status = status;
        this.message = message;
        this.cooks = cooks;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Cook[] getCooks() {
        return cooks;
    }
}
