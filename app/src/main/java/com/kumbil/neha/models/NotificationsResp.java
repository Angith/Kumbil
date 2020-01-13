package com.kumbil.neha.models;

import com.google.gson.annotations.SerializedName;

public class NotificationsResp {
    @SerializedName("status")
    int status;
    @SerializedName("message")
    String message;
    @SerializedName("notifications")
    dNotification dn[];

    public NotificationsResp(int status, String message, dNotification[] dn) {
        this.status = status;
        this.message = message;
        this.dn = dn;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public dNotification[] getDn() {
        return dn;
    }
}
