package com.kumbil.neha;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kumbil.neha.Network.ApiClient;
import com.kumbil.neha.Network.ApiInterface;
import com.kumbil.neha.models.menu;
import com.kumbil.neha.models.menuResp;
import com.kumbil.neha.shared.SharedData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cookhomeActivity extends AppCompatActivity {
    ImageButton addMenu;
    TextView cooksName;
    private ArrayList<CookMenu> menuItems = new ArrayList<>();
    Context context = this;
    @Override
    public boolean onCreateOptionsMenu(Menu mn) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tool, mn);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookhome);
        cooksName = (TextView) findViewById(R.id.etCooksNameValue);
        addMenu = (ImageButton) findViewById(R.id.addCookMenu);
        String name = SharedData.getDefaults("NAME", GlobalContext.context);
        cooksName.setText(name);
        int id = Integer.parseInt(SharedData.getDefaults("ID", GlobalContext.context));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<menuResp> getMenuCall = apiInterface.getMenu(id);
        getMenuCall.enqueue(new Callback<menuResp>() {
            @Override
            public void onResponse(Call<menuResp> call, Response<menuResp> response) {
                menuResp resp = response.body();
                if(resp.getStatus() == 0 && resp.getMn().length > 0 )
                {
                    RecyclerView menus = (RecyclerView) findViewById(R.id.menu_rv);
                    menus.setLayoutManager(new LinearLayoutManager(context));
                    menus.setAdapter(new CookMenuRVAdapter(menuItems, context));
                    menu[] mns = resp.getMn();
                    for(int i = 0; i < resp.getMn().length; i++) {
                        menuItems.add(new CookMenu(mns[i].getDishName(),mns[i].getPrice(),mns[i].getDescription()));
                    }

                } else if(resp.getStatus() == 0 && resp.getMn().length == 0) {
                    CreateMenuAlert cma = new CreateMenuAlert();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack(null);
                    cma.show(ft, "dialog");
                } else {
                    Toast.makeText(cookhomeActivity.this,resp.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<menuResp> call, Throwable t) {
                call.cancel();
            }
        });

        addMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cookhomeActivity.this, CreatemenuActivity.class);
                startActivity(intent);
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
}
