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
import com.kumbil.neha.models.menu;
import com.kumbil.neha.models.menuResp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerMenuActivity extends AppCompatActivity implements CreateAlert.OnCompleteListener{

    private ArrayList<CustomerMenu> cMenus = new ArrayList<>();
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
        setContentView(R.layout.activity_customer_menu);
        Intent mIntent = getIntent();
        final int id = mIntent.getIntExtra("id", 0);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<menuResp> getMenuCall = apiInterface.getMenu(id);
        getMenuCall.enqueue(new Callback<menuResp>() {
            @Override
            public void onResponse(Call<menuResp> call, Response<menuResp> response) {
                menuResp resp = response.body();
                if(resp.getStatus() == 0 && resp.getMn().length > 0 )
                {
                    menu[] mns = resp.getMn();
                    for(int i = 0; i < resp.getMn().length; i++) {
                        cMenus.add(new CustomerMenu(mns[i].getDishId(), mns[i].getDishName(),
                                mns[i].getPrice(),mns[i].getDescription()));
                    }
                    RecyclerView menus = (RecyclerView) findViewById(R.id.customer_menu_rv);
                    menus.setLayoutManager(new LinearLayoutManager(context));
                    menus.setAdapter(new CustomerMenuRVAdapter(cMenus, context, new ClickListener() {
                        @Override
                        public void onPositionClicked(int position, boolean accept) {
                            int dishId = cMenus.get(position).getId();
                            Intent intn = new Intent(getApplicationContext(),customer_order.class);
                            intn.putExtra("dishId",dishId);
                            intn.putExtra("cookId",id);
                            startActivity(intn);

                        }
                    }));
                } else if(resp.getStatus() == 0 && resp.getMn().length == 0) {
                    CreateAlert ca = CreateAlert.newInstance("No items found");
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack(null);
                    ca.show(ft, "dialog");
                } else {
                    Toast.makeText(CustomerMenuActivity.this,resp.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<menuResp> call, Throwable t) {
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