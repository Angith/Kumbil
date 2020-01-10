package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class Cook {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("place")
    private String place;

    public Cook(int id, String name, String place) {
        this.id = id;
        this.name = name;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }
}

