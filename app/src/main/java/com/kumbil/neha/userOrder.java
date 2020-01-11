package com.kumbil.neha;

public class userOrder {

    String DishName;
    String date;
    String time;
    int Quantity;
    String status;

    public userOrder(String dishName, String date, String time, int Quantity, String status) {
        this.DishName = dishName;
        this.date = date;
        this.time = time;
        this.Quantity = Quantity;
        this.status = status;
    }

    public String getDishName() {
        return DishName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getStatus() {
        return status;
    }
}
