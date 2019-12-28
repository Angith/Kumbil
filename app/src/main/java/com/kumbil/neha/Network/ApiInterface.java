package com.kumbil.neha.Network;

import com.kumbil.neha.models.LoginResp;
import com.kumbil.neha.models.Resp;
import com.kumbil.neha.models.User;
import com.kumbil.neha.models.loginUser;
import com.kumbil.neha.models.menu;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("kumbil/register.php")
    Call<Resp> createuser(@Body User user);
    @POST("kumbil/login.php")
    Call<LoginResp> login(@Body loginUser user);
    @POST("kumbil/postMenu.php")
    Call<Resp> createmenu(@Body menu mn);

}
