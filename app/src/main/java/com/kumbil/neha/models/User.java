package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
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

    public  User(String name, String phone,
                 String email, String place, String type, String password, String loggedIn) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.place = place;
        this.type = type;
        this.password = password;
        this.loggedIn = loggedIn;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(String loggedIn) {
        this.loggedIn = loggedIn;
    }
}
