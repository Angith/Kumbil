package com.kumbil.neha.Network;

import com.kumbil.neha.models.Resp;
import com.kumbil.neha.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("kumbil/register.php")
    Call<Resp> createuser(@Body User user);
}
