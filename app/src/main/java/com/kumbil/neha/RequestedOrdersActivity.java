package com.kumbil.neha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class RequestedOrdersActivity extends AppCompatActivity {

    private ArrayList<Orders> mOrders = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tools, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        RecyclerView rvOrders = (RecyclerView) findViewById(R.id.orders_rv);
        rvOrders.setLayoutManager(new LinearLayoutManager(this));
        rvOrders.setAdapter(new OrderRVAdapter(mOrders, this));
        mOrders.add(new Orders("Neha", "biriyani",
                "10-2-2018 Wed  12pm", "1",  "null"));
        mOrders.add(new Orders("Nayana", "alpham manthi",
                "10-2-2018 Wed 12pm", "1", "null"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                Intent intn = new Intent(getApplicationContext(),CartActivity.class);
                //intn.putExtra("Name",uname);
                startActivity(intn);
                // User chose the "Settings" item, show the app settings UI...
                return true;
            case R.id.action_search:
                Intent i = new Intent(getApplicationContext(),CustomerActivity.class);
                //intn.putExtra("Name",uname);
                startActivity(i);
                // User chose the "Settings" item, show the app settings UI...
                return true;
            case R.id.action_myorders:
                Intent in = new Intent(getApplicationContext(),RequestedOrdersActivity.class);
                // in.putExtra("Name",uname);
                // startActivity(in);

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
}
