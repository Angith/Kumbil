package com.kumbil.neha;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.kumbil.neha.Network.ApiClient;
import com.kumbil.neha.Network.ApiInterface;
import com.kumbil.neha.models.Resp;
import com.kumbil.neha.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register2_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText username,password,place,email,phoneNo;
    Button Register;
    Spinner type;
    String []typedata={"Cook","Delivery","Customer"};
    boolean isSuccess = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2_);
        username=(EditText)findViewById(R.id.etUsername);
        password=(EditText)findViewById(R.id.etPassword);
        place=(EditText)findViewById(R.id.etPlace);
        email=(EditText)findViewById(R.id.etEmail);
        phoneNo=(EditText)findViewById(R.id.etPhone);
        Register=(Button)findViewById(R.id.bRegister);
        type=(Spinner)findViewById(R.id.type);
        ArrayAdapter spindata=new ArrayAdapter(this,android.R.layout.simple_spinner_item,typedata);
        spindata.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(spindata);
        type.setOnItemSelectedListener(this);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(RegisterCheck()) {
//                    Toast.makeText(Register2_Activity.this,"Register success",Toast.LENGTH_SHORT).show();
//                    Intent i=new Intent(Register2_Activity.this,cookhomeActivity.class);
//                    startActivity(i);
//                }
//                else
//                {
//                    Toast.makeText(Register2_Activity.this, "failed", Toast.LENGTH_SHORT).show();
//                }
                RegisterCheck();

                }
        });


    }
    public void RegisterCheck(){
        final AlertDialog.Builder sets = new AlertDialog.Builder(this);
        sets.setTitle("Error");
        sets.setIcon(android.R.drawable.btn_star_big_on);
        sets.setPositiveButton("Close", null);
        if (username.getText().length() == 0) {
            sets.setMessage("Please input[name]");
            sets.show();
            username.requestFocus();

        }


        if (password.getText().length() == 0) {
            sets.setMessage("Please input password");
            sets.show();
            password.requestFocus();

        }
        if (place.getText().length() == 0) {
            sets.setMessage("Please input[place]");
            sets.show();
            place.requestFocus();
        }
        if (email.getText().length() == 0) {
            sets.setMessage("Please input email");
            sets.show();
            email.requestFocus();
        }
        if (phoneNo.getText().length() == 0) {
            sets.setMessage("Please input[phone]");
            sets.show();
            phoneNo.requestFocus();
        }

        String userType = type.getSelectedItem().toString();


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        User user = new User(
                username.getText().toString(),
                phoneNo.getText().toString(),
                email.getText().toString(),
                place.getText().toString(),
                userType,
                password.getText().toString(), "false");
        Call<Resp> registerCall = apiInterface.createuser(user);
        registerCall.enqueue(new Callback<Resp>() {
            @Override
            public void onResponse(Call<Resp> call, Response<Resp> response) {

                Resp resp = response.body();
                if(resp.getStatus() !=0)
                {
                    sets.setMessage(resp.getMessage());
                    sets.show();
                    isSuccess = false;
                } else {
                    Toast.makeText(Register2_Activity.this,"Register success",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Register2_Activity.this,cookhomeActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<Resp> call, Throwable t) {
                call.cancel();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
