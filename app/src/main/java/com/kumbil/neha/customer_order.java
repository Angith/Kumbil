package com.kumbil.neha;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kumbil.neha.Network.ApiClient;
import com.kumbil.neha.Network.ApiInterface;
import com.kumbil.neha.models.PostOrder;
import com.kumbil.neha.models.Resp;
import com.kumbil.neha.models.User;
import com.kumbil.neha.shared.SharedData;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class customer_order extends AppCompatActivity implements CreateAlert.OnCompleteListener{
EditText quantity,time, date;
Button cancel,buy;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tools, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_order);
        quantity= (EditText) findViewById(R.id.etqty);
        time= (EditText) findViewById(R.id.etTime);
        date= (EditText) findViewById(R.id.etDate);
        cancel= (Button) findViewById(R.id.bCancel);
        buy= (Button) findViewById(R.id.bRegister);
        Intent mIntent = getIntent();
        final int dishId = mIntent.getIntExtra("dishId", 0);
        final int cookId = mIntent.getIntExtra("cookId", 0);
        final int customerId =  Integer.parseInt(SharedData.getDefaults("ID", GlobalContext.context));
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(OrderCheck()) {
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    PostOrder pOrder = new PostOrder(
                            cookId,
                            customerId,
                            dishId,
                            "placed",
                            date.getText().toString(),
                            time.getText().toString(),
                            Integer.parseInt(quantity.getText().toString()));
                    Call<Resp> postOrderCall = apiInterface.postOrder(pOrder);
                    postOrderCall.enqueue(new Callback<Resp>() {
                        @Override
                        public void onResponse(Call<Resp> call, Response<Resp> response) {
                            Resp resp = response.body();
                            if(resp.getStatus() == 0) {
                                CreateAlert ca = CreateAlert.newInstance("Order placed");
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                                if (prev != null) {
                                    ft.remove(prev);
                                }
                                ft.addToBackStack(null);
                                ca.show(ft, "dialog");
                            } else {
                                CreateAlert ca = CreateAlert.newInstance(resp.getMessage());
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                                if (prev != null) {
                                    ft.remove(prev);
                                }
                                ft.addToBackStack(null);
                                ca.show(ft, "dialog");
                            }

                        }
                        @Override
                        public void onFailure(Call<Resp> call, Throwable t) {
                            call.cancel();
                        }
                    });
                }
                else
                {
                    Toast.makeText(customer_order.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(customer_order.this,CustomerActivity.class);
                startActivity(i);
            }
        });

    }
    public boolean OrderCheck(){
        final AlertDialog.Builder sets = new AlertDialog.Builder(this);
        sets.setTitle("Error");
        sets.setIcon(android.R.drawable.btn_star_big_on);
        sets.setPositiveButton("Close", null);

        if (quantity.getText().length() == 0) {
            sets.setMessage("Please input quantity");
            sets.show();
            quantity.requestFocus();
            return false;

        }
        if (time.getText().length() == 0) {
            sets.setMessage("Please input time");
            sets.show();
            time.requestFocus();
            return false;

        }

        if (date.getText().length() == 0) {
            sets.setMessage("Please input date");
            sets.show();
            time.requestFocus();
            return false;

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent i = new Intent(getApplicationContext(),CustomerActivity.class);
                //intn.putExtra("Name",uname);
                startActivity(i);
                // User chose the "Settings" item, show the app settings UI...
                return true;
            case R.id.action_myorders:
                Intent in = new Intent(getApplicationContext(),CustomerOrders.class);
                startActivity(in);
                return true;
            case R.id.action_logout:
                Intent intent = new Intent(getApplicationContext(),loginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                // User chose the "Settings" item, show the app settings UI...
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onComplete(boolean ok) {

    }
}




