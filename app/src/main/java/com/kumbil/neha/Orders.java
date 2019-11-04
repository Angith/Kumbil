package com.kumbil.neha;

public class Orders {

    String UserName;
    String DishName;
    String deliveryTime;
    String Quantity;
    String dAddress;

    public Orders(String userName, String dishName, String time, String Quantity, String dAddress) {
        this.UserName = userName;
        this.DishName = dishName;
        this.deliveryTime = time;
        this.Quantity = Quantity;
        this.dAddress = dAddress;
    }

    public String getUserName() {
        return UserName;
    }

    public String getDishName() {
        return DishName;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getdAddress() {
        return dAddress;
    }

    public String getQuantity() {
        return Quantity;
    }
}
