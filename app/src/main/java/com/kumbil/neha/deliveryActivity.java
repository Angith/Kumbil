package com.kumbil.neha;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.kumbil.neha.models.Delivery;
import com.kumbil.neha.models.dNotification;

import java.util.ArrayList;

public class deliveryActivity extends AppCompatActivity {

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
        RecyclerView rvOrders = (RecyclerView) findViewById(R.id.notifications_rv);
        rvOrders.setLayoutManager(new LinearLayoutManager(context));
        rvOrders.setAdapter(new DeliveryNotificationAdapter(mOrders, context));

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
}
