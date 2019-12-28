package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class loginUser {
    @SerializedName("username")
    private String name;
    @SerializedName("password")
    private String password;

    public loginUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
