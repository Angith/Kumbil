package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class Resp {
    @SerializedName("status")
    int status;
    @SerializedName("message")
    String message;

    public Resp(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
