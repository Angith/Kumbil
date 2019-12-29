package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class menuResp {
    @SerializedName("status")
    int status;
    @SerializedName("message")
    String message;
    @SerializedName("menu")
    menu mn[];

    public menuResp(int status, String message, menu[] mn) {
        this.status = status;
        this.message = message;
        this.mn = mn;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public menu[] getMn() {
        return mn;
    }
}
