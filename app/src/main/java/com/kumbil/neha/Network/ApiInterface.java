package com.kumbil.neha.Network;

import com.kumbil.neha.models.LoginResp;
import com.kumbil.neha.models.Resp;
import com.kumbil.neha.models.UpdateOrder;
import com.kumbil.neha.models.User;
import com.kumbil.neha.models.loginUser;
import com.kumbil.neha.models.menu;
import com.kumbil.neha.models.menuResp;
import com.kumbil.neha.models.ordersResp;

import okhttp3.RequestBody;
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

    @GET("kumbil/getMenu.php")
    Call<menuResp> getMenu(@Query("id") int id);
    @GET("kumbil/getOrders.php")
    Call<ordersResp> getOrders(@Query("status") String status, @Query("cookId") int id );

    @PATCH("kumbil/updateOrder.php")
    Call<Resp> updateOrders(@Body UpdateOrder uo);

}
