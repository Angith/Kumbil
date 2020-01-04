package com.kumbil.neha;

public class Orders {

    String UserName;
    String DishName;
    String date;
    String time;
    int Quantity;
    String dAddress;

    public Orders(String userName, String dishName, String date, String time, int Quantity, String dAddress) {
        this.UserName = userName;
        this.DishName = dishName;
        this.date = date;
        this.time = time;
        this.Quantity = Quantity;
        this.dAddress = dAddress;
    }

    public String getUserName() {
        return UserName;
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

    public String getdAddress() {
        return dAddress;
    }

    public int getQuantity() {
        return Quantity;
    }
}
