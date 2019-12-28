package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class LoginRespUser {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;
    @SerializedName("place")
    private String place;
    @SerializedName("type")
    private String type;
    @SerializedName("password")
    private String password;
    @SerializedName("loggedIn")
    private String loggedIn;

    public LoginRespUser(int id, String name, String phone,
                         String email, String place, String type, String password, String loggedIn) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.place = place;
        this.type = type;
        this.password = password;
        this.loggedIn = loggedIn;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPlace() {
        return place;
    }

    public String getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }

    public String getLoggedIn() {
        return loggedIn;
    }
}
