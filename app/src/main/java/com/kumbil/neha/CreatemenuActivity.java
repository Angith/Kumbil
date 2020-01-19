package com.kumbil.neha;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kumbil.neha.Network.ApiClient;
import com.kumbil.neha.Network.ApiInterface;
import com.kumbil.neha.models.Resp;
import com.kumbil.neha.models.User;
import com.kumbil.neha.models.menu;
import com.kumbil.neha.shared.SharedData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatemenuActivity extends AppCompatActivity {
    EditText DishName, Price, Description;
    Button Create, Cancel;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tool, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createmenu);
        DishName = (EditText) findViewById(R.id.etDish);
        Price = (EditText) findViewById(R.id.etPrice);
        Description = (EditText) findViewById(R.id.etDes);
        Create = (Button) findViewById(R.id.btCreate);
        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMenu();
            }
        });
        Cancel = (Button) findViewById(R.id.bCancel);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreatemenuActivity.this, cookhomeActivity.class);
                startActivity(i);
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
            case R.id.action_profile:
                Intent pa = new Intent(getApplicationContext(),profileActivity.class);
                //intn.putExtra("Name",uname);
                startActivity(pa);
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

    public void createMenu() {
        final AlertDialog.Builder sets = new AlertDialog.Builder(this);
        sets.setTitle("Error");
        sets.setIcon(android.R.drawable.btn_star_big_on);
        sets.setPositiveButton("Close", null);
        if (DishName.getText().length() == 0) {
            sets.setMessage("Please input dishname]");
            sets.show();
            DishName.requestFocus();
        }

        if (Price.getText().length() == 0) {
            sets.setMessage("Please input price");
            sets.show();
            Price.requestFocus();

        }

        if (Description.getText().length() == 0) {
            sets.setMessage("Please input description");
            sets.show();
            Description.requestFocus();
        }
    int id = Integer.parseInt(SharedData.getDefaults("ID", GlobalContext.context));
        float price = Float.valueOf(Price.getText().toString());
    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    menu mn = new menu(
            id,
            DishName.getText().toString(),
            price,
            Description.getText().toString());
    Call<Resp> menuCall = apiInterface.createmenu(mn);
        menuCall.enqueue(new Callback<Resp>() {
        @Override
        public void onResponse(Call<Resp> call, Response<Resp> response) {
            Resp resp = response.body();
            if(resp.getStatus() !=0)
            {
                sets.setMessage(resp.getMessage());
                sets.show();
            } else {
                Toast.makeText(CreatemenuActivity.this,"item added",Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }
        }

        @Override
        public void onFailure(Call<Resp> call, Throwable t) {
            call.cancel();
        }
    });

    }

}



