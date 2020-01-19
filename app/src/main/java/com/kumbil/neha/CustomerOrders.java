package com.kumbil.neha;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.kumbil.neha.Network.ApiClient;
import com.kumbil.neha.Network.ApiInterface;
import com.kumbil.neha.models.Order;

import com.kumbil.neha.models.ordersResp;
import com.kumbil.neha.shared.SharedData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerOrders extends AppCompatActivity {

    private ArrayList<userOrder> cOrders = new ArrayList<>();
    Context context = this;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tools, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_orders);

        int id = Integer.parseInt(SharedData.getDefaults("ID", GlobalContext.context));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ordersResp> getUserOrdersCall = apiInterface.getUserOrders(id);
        getUserOrdersCall.enqueue(new Callback<ordersResp>() {
            @Override
            public void onResponse(Call<ordersResp> call, Response<ordersResp> response) {
                ordersResp resp = response.body();
                if (resp.getStatus() == 0 && resp.getOrdrs().length > 0) {
                    Order[] ordrs = resp.getOrdrs();
                    for (int i = 0; i < resp.getOrdrs().length; i++) {
                        cOrders.add(new userOrder(ordrs[i].getDishName(), ordrs[i].getDate(),
                                ordrs[i].getTime(),ordrs[i].getQuantity(),ordrs[i].getStatus()));
                    }
                    RecyclerView rvOrders = (RecyclerView) findViewById(R.id.customer_orders_rv);
                    rvOrders.setLayoutManager(new LinearLayoutManager(context));
                    rvOrders.setAdapter(new CustomerOrdersRVAdapter(cOrders, context));
                } else if(resp.getStatus() == 0 && resp.getOrdrs().length == 0) {
                    CreateAlert ca = CreateAlert.newInstance("Orders empty");
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack(null);
                    ca.show(ft, "dialog");
                } else {
                    Toast.makeText(CustomerOrders.this,resp.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ordersResp> call, Throwable t) {
                call.cancel();
            }
        });

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
            case R.id.action_profile:
                Intent pa = new Intent(getApplicationContext(),profileActivity.class);
                //intn.putExtra("Name",uname);
                startActivity(pa);
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
}
