package com.kumbil.neha;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kumbil.neha.Network.ApiClient;
import com.kumbil.neha.Network.ApiInterface;
import com.kumbil.neha.models.LoginResp;
import com.kumbil.neha.models.LoginRespUser;
import com.kumbil.neha.models.loginUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {
    EditText username,password;
    Button login,createnewaccount;
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.etUsername);
        password= (EditText) findViewById(R.id.etPassword);
        login= (Button) findViewById(R.id.bLogin);
        createnewaccount= (Button) findViewById(R.id.bCreateAccount);
        forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        createnewaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(loginActivity.this,Register2_Activity.class);
                startActivity(i);
            }
        });

    }
    public void login(){
        final AlertDialog.Builder sets = new AlertDialog.Builder(this);
        sets.setTitle("Error");
        sets.setIcon(android.R.drawable.btn_star_big_on);
        sets.setPositiveButton("Close", null);
        if (username.getText().length() == 0) {
            sets.setMessage("Please input name");
            sets.show();
            username.requestFocus();
        }
        else if (password.getText().length() == 0) {
            sets.setMessage("Please input password");
            sets.show();
            password.requestFocus();
        }
        else {

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            loginUser user = new loginUser(
                    username.getText().toString(),
                    password.getText().toString());
            Call<LoginResp> loginCall = apiInterface.login(user);
            loginCall.enqueue(new Callback<LoginResp>() {
                @Override
                public void onResponse(Call<LoginResp> call, Response<LoginResp> response) {
                    LoginResp resp = response.body();
                    if (resp.getStatus() != 0) {
                        sets.setMessage(resp.getMessage());
                        sets.show();
                    } else {
                        LoginRespUser user = resp.getUser();
                        if (user.getType().equals("Cook")) {
                            Toast.makeText(loginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(loginActivity.this, cookhomeActivity.class);
                            startActivity(i);
                            finish();
                        } else if (user.getType().equals("Customer")) {
                            Toast.makeText(loginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(loginActivity.this, CustomerActivity.class);
                            startActivity(i);
                            finish();
                        } else if (user.getType().equals("Delivery")) {
                            Toast.makeText(loginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(loginActivity.this, CustomerActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(loginActivity.this, "Login failed : Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResp> call, Throwable t) {
                    call.cancel();
                }
            });
        }
    }
}






