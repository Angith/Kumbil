package com.kumbil.neha;

public class Cooks {

    int id;
    String cook;
    String place;

    public Cooks(String cook, String place, int id) {
        this.cook = cook;
        this.place = place;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCook() {
        return cook;
    }

    public String getPlace() {
        return place;
    }
}
