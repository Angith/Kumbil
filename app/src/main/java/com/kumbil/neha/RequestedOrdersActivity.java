package com.kumbil.neha;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.kumbil.neha.Network.ApiClient;
import com.kumbil.neha.Network.ApiInterface;
import com.kumbil.neha.models.Order;
import com.kumbil.neha.models.Resp;
import com.kumbil.neha.models.UpdateOrder;
import com.kumbil.neha.models.menu;
import com.kumbil.neha.models.menuResp;
import com.kumbil.neha.models.ordersResp;
import com.kumbil.neha.shared.SharedData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestedOrdersActivity extends AppCompatActivity implements CreateAlert.OnCompleteListener {

    private ArrayList<Orders> mOrders = new ArrayList<>();
    Context context = this;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tool, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        int id = Integer.parseInt(SharedData.getDefaults("ID", GlobalContext.context));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ordersResp> getOrdersCall = apiInterface.getOrders("placed", id);

        getOrdersCall.enqueue(new Callback<ordersResp>() {
            @Override
            public void onResponse(Call<ordersResp> call, Response<ordersResp> response) {
                ordersResp resp = response.body();
                if(resp.getStatus() == 0 && resp.getOrdrs().length > 0 )
                {
                    Order[] ordrs = resp.getOrdrs();
                    for(int i = 0; i < resp.getOrdrs().length; i++) {
                        mOrders.add(new Orders(ordrs[i].getId(),ordrs[i].getName(), ordrs[i].getDishName(), ordrs[i].getDate(),
                                ordrs[i].getTime(), ordrs[i].getQuantity(), ordrs[i].getAddress()));
                    }
                    RecyclerView rvOrders = (RecyclerView) findViewById(R.id.orders_rv);
                    rvOrders.setLayoutManager(new LinearLayoutManager(context));
                    rvOrders.setAdapter(new OrderRVAdapter(mOrders, context, new ClickListener() {
                        @Override
                        public void onPositionClicked(int position, boolean accept) {
                            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                            if(accept) {
                                UpdateOrder update = new UpdateOrder("accepted", mOrders.get(position).getId());
                                Call<Resp> getUpdateCall = apiInterface.updateOrders(update);
                                getUpdateCall.enqueue(new Callback<Resp>() {
                                    @Override
                                    public void onResponse(Call<Resp> call, Response<Resp> response) {
                                        Resp r = response.body();
                                        if(r.getStatus() == 0) {
                                            CreateAlert ca = CreateAlert.newInstance("Order Accepted");
                                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                            Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                                            if (prev != null) {
                                                ft.remove(prev);
                                            }
                                            ft.addToBackStack(null);
                                            ca.show(ft, "dialog");
                                        } else {
                                            CreateAlert ca = CreateAlert.newInstance("Order update failed");
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
                            } else {
                                UpdateOrder update = new UpdateOrder("rejected", mOrders.get(position).getId());
                                Call<Resp> getUpdateCall = apiInterface.updateOrders(update);
                                getUpdateCall.enqueue(new Callback<Resp>() {
                                    @Override
                                    public void onResponse(Call<Resp> call, Response<Resp> response) {
                                        Resp r = response.body();
                                        if(r.getStatus() == 0) {
                                            CreateAlert ca = CreateAlert.newInstance("Order Rejected");
                                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                            Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                                            if (prev != null) {
                                                ft.remove(prev);
                                            }
                                            ft.addToBackStack(null);
                                            ca.show(ft, "dialog");
                                        } else {
                                            CreateAlert ca = CreateAlert.newInstance("Order update failed");
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
                        }
                    }));

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
                    Toast.makeText(RequestedOrdersActivity.this,resp.getMessage(),Toast.LENGTH_SHORT).show();
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
            case R.id.action_home:
                Intent intn = new Intent(getApplicationContext(),cookhomeActivity.class);
                //intn.putExtra("Name",uname);
                startActivity(intn);
                // User chose the "Settings" item, show the app settings UI...
                return true;
            case R.id.action_orders:
                Intent i = new Intent(getApplicationContext(),RequestedOrdersActivity.class);
                //intn.putExtra("Name",uname);
                startActivity(i);
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_p_orders:
                Intent in = new Intent(getApplicationContext(),PendingOrdersActivity.class);
                //intn.putExtra("Name",uname);
                startActivity(in);
                // User chose the "Settings" item, show the app settings UI...
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
        if(ok) {
            int id = Integer.parseInt(SharedData.getDefaults("ID", GlobalContext.context));
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<ordersResp> getOrdersCall = apiInterface.getOrders("placed", id);

            getOrdersCall.enqueue(new Callback<ordersResp>() {
                @Override
                public void onResponse(Call<ordersResp> call, Response<ordersResp> response) {
                    ordersResp resp = response.body();
                    if(resp.getStatus() == 0 && resp.getOrdrs().length > 0 ) {
                        Intent intn = new Intent(getApplicationContext(),RequestedOrdersActivity.class);
                        startActivity(intn);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<ordersResp> call, Throwable t) {
                    call.cancel();
                }
            });
        }
    }
}
