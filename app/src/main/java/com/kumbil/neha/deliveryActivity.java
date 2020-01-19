package com.kumbil.neha;

import android.app.Notification;
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

import com.kumbil.neha.Network.ApiClient;
import com.kumbil.neha.Network.ApiInterface;
import com.kumbil.neha.models.Delivery;
import com.kumbil.neha.models.NotificationsResp;
import com.kumbil.neha.models.Order;
import com.kumbil.neha.models.Resp;
import com.kumbil.neha.models.dNotification;
import com.kumbil.neha.models.ordersResp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class deliveryActivity extends AppCompatActivity implements CreateAlert.OnCompleteListener {

    private ArrayList<dNotification> mOrders = new ArrayList<>();
    Context context = this;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolss, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<NotificationsResp> getNotificationsCall = apiInterface.getNotifications("cooked");
        getNotificationsCall.enqueue(new Callback<NotificationsResp>() {
            @Override
            public void onResponse(Call<NotificationsResp> call, Response<NotificationsResp> response) {
                final NotificationsResp res = response.body();
                if (res.getStatus() == 0 && res.getDn().length > 0) {
                    dNotification[] dn = res.getDn();
                    for(int i=0; i< res.getDn().length; i++){
                        mOrders.add(new dNotification(dn[i].getId(),dn[i].getsAddress(),dn[i].getdAddress(),
                                dn[i].getDeliveryTime(), dn[i].getStatus()));
                    }
                    RecyclerView rvOrders = (RecyclerView) findViewById(R.id.notifications_rv);
                    rvOrders.setLayoutManager(new LinearLayoutManager(context));
                    rvOrders.setAdapter(new DeliveryNotificationAdapter(mOrders, context, new ClickListener() {
                        @Override
                        public void onPositionClicked(int position, boolean accept) {
                            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                            Call<Resp> updateCall =
                                    apiInterface.updateNotification(mOrders.get(position).getId());
                            updateCall.enqueue(new Callback<Resp>() {
                                @Override
                                public void onResponse(Call<Resp> call, Response<Resp> response) {
                                    Resp resp = response.body();
                                    if (resp.getStatus() == 0) {
                                        CreateAlert ca = CreateAlert.newInstance("Delivery completed");
                                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                                        if (prev != null) {
                                            ft.remove(prev);
                                        }
                                        ft.addToBackStack(null);
                                        ca.show(ft, "dialog");
                                    } else {
                                        CreateAlert ca = CreateAlert.newInstance("Could not update. Please try again later");
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
                    }));
                } else if (res.getStatus() == 0 && res.getDn().length == 0) {

                }
            }

            @Override
            public void onFailure(Call<NotificationsResp> call, Throwable t) {
                call.cancel();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
        if(ok){
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<NotificationsResp> getNotificationsCall = apiInterface.getNotifications("cooked");
            getNotificationsCall.enqueue(new Callback<NotificationsResp>() {
                @Override
                public void onResponse(Call<NotificationsResp> call, Response<NotificationsResp> response) {
                    final NotificationsResp res = response.body();
                    if (res.getStatus() == 0 && res.getDn().length > 0) {
                        Intent intn = new Intent(getApplicationContext(),deliveryActivity.class);
                        startActivity(intn);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<NotificationsResp> call, Throwable t) {
                    call.cancel();
                }
            });

        }
    }
}
