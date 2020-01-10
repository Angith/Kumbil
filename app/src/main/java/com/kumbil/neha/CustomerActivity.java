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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kumbil.neha.Network.ApiClient;
import com.kumbil.neha.Network.ApiInterface;
import com.kumbil.neha.models.Cook;
import com.kumbil.neha.models.User;
import com.kumbil.neha.models.menu;
import com.kumbil.neha.models.menuResp;
import com.kumbil.neha.models.searchResp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerActivity extends AppCompatActivity implements CreateAlert.OnCompleteListener{
    private ArrayList<Cooks> cooks = new ArrayList<>();
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
        setContentView(R.layout.activity_customer);
        final EditText searchKey = (EditText) findViewById(R.id.et_place);
        Button searchButton = (Button) findViewById(R.id.bt_search_cooks);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = searchKey.getText().toString();
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<searchResp> getUsers = apiInterface.getUsers(keyword, "Cook");
                getUsers.enqueue(new Callback<searchResp>() {
                    @Override
                    public void onResponse(Call<searchResp> call, Response<searchResp> response) {
                        searchResp resp = response.body();
                        if(resp.getStatus() == 0 && resp.getCooks().length > 0 )
                        {
                            Cook[] cks = resp.getCooks();
                            for(int i = 0; i < resp.getCooks().length; i++) {
                                cooks.add(new Cooks(cks[i].getName(),cks[i].getPlace(), cks[i].getId()));
                            }
                            RecyclerView rvOrders = (RecyclerView) findViewById(R.id.cooks_rv);
                            rvOrders.setLayoutManager(new LinearLayoutManager(context));
                            rvOrders.setAdapter(new CooksRVAdapter(cooks, context, new ClickListener() {
                                @Override
                                public void onPositionClicked(int position, boolean accept) {
                                    int id = cooks.get(position).getId();
                                    Intent intn = new Intent(getApplicationContext(),CustomerMenuActivity.class);
                                    intn.putExtra("id",id);
                                    startActivity(intn);
                                }
                            }));
                        } else if(resp.getStatus() == 0 && resp.getCooks().length == 0) {
                            CreateAlert ca = CreateAlert.newInstance("No cooks found");
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                            if (prev != null) {
                                ft.remove(prev);
                            }
                            ft.addToBackStack(null);
                            ca.show(ft, "dialog");
                        } else {
                            Toast.makeText(CustomerActivity.this,resp.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<searchResp> call, Throwable t) {

                    }
                });
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                Intent intn = new Intent(getApplicationContext(),CustomerMenuActivity.class);
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

    @Override
    public void onComplete(boolean ok) {

    }
}
