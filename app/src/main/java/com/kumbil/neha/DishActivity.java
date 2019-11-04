package com.kumbil.neha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DishActivity extends AppCompatActivity {
    EditText Name,Price,Description;
    Button Edit;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tool, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
        Name=(EditText) findViewById(R.id.etName);
        Price=(EditText) findViewById(R.id.etPrice);
        Description=(EditText) findViewById(R.id.etName);
        Edit=(Button)findViewById(R.id.bEdit);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DishActivity.this,EditdishActivity.class);
                startActivity(i);
            }
        });




    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Intent intn = new Intent(getApplicationContext(),EditdishActivity.class);
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
            case R.id.action_notif:
                //Intent in = new Intent(getApplicationContext(),RescueCpassword.class);
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
