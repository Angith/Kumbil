package com.kumbil.neha.Network;

import com.kumbil.neha.models.Delivery;
import com.kumbil.neha.models.LoginResp;
import com.kumbil.neha.models.NotificationsResp;
import com.kumbil.neha.models.PostOrder;
import com.kumbil.neha.models.Resp;
import com.kumbil.neha.models.UpdateOrder;
import com.kumbil.neha.models.User;
import com.kumbil.neha.models.loginUser;
import com.kumbil.neha.models.menu;
import com.kumbil.neha.models.menuResp;
import com.kumbil.neha.models.ordersResp;
import com.kumbil.neha.models.searchResp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("kumbil/register.php")
    Call<Resp> createuser(@Body User user);
    @POST("kumbil/login.php")
    Call<LoginResp> login(@Body loginUser user);
    @POST("kumbil/postMenu.php")
    Call<Resp> createmenu(@Body menu mn);
    @POST("kumbil/postOrder.php")
    Call<Resp> postOrder(@Body PostOrder po);
    @POST("kumbil/deliveryNotification.php")
    Call<Resp> notify(@Body Delivery dry);

    @GET("kumbil/getMenu.php")
    Call<menuResp> getMenu(@Query("id") int id);
    @GET("kumbil/getUser.php")
    Call<LoginResp> getUser(@Query("id") int id);
    @GET("kumbil/getOrders.php")
    Call<ordersResp> getOrders(@Query("status") String status, @Query("cookId") int id );
    @GET("kumbil/getUserOrders.php")
    Call<ordersResp> getUserOrders(@Query("userId") int id );
    @GET("kumbil/getUsers.php")
    Call<searchResp> getUsers(@Query("key") String key, @Query("type") String type );
    @GET("kumbil/getNotifications.php")
    Call<NotificationsResp> getNotifications(@Query("status") String status);

    @PATCH("kumbil/updateOrder.php")
    Call<Resp> updateOrders(@Body UpdateOrder uo);
    @PATCH("kumbil/updateNotification.php")
    Call<Resp> updateNotification(@Query("id") int id);

}
