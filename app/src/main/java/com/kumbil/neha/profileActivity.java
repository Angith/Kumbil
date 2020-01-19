package com.kumbil.neha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kumbil.neha.Network.ApiClient;
import com.kumbil.neha.Network.ApiInterface;
import com.kumbil.neha.models.LoginResp;
import com.kumbil.neha.shared.SharedData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profileActivity extends AppCompatActivity {

    TextView name, phone, email, place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = (TextView) findViewById(R.id.tvName);
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.tvEmail);
        place = (TextView) findViewById(R.id.tvPlace);
        final int id = Integer.parseInt(SharedData.getDefaults("ID", GlobalContext.context));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResp> getUserCall = apiInterface.getUser(id);
        getUserCall.enqueue(new Callback<LoginResp>() {
            @Override
            public void onResponse(Call<LoginResp> call, Response<LoginResp> response) {
                LoginResp resp = response.body();
                if(resp.getUser().getId() == id){
                    name.setText(resp.getUser().getName());
                    phone.setText(resp.getUser().getPhone());
                    email.setText(resp.getUser().getEmail());
                    place.setText(resp.getUser().getPlace());
                }
            }

            @Override
            public void onFailure(Call<LoginResp> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
