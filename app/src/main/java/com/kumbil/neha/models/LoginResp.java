package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class LoginResp {

    @SerializedName("status")
    int status;
    @SerializedName("message")
    String message;
    @SerializedName("user")
    LoginRespUser user;

    public LoginResp(int status, String message, LoginRespUser user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LoginRespUser getUser() {
        return user;
    }
}
