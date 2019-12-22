package com.kumbil.neha;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editProfile extends AppCompatActivity {
    EditText username,phone,address;
    Button cancel,update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_order);
        username = (EditText) findViewById(R.id.etUsername);
        phone= (EditText) findViewById(R.id.etphone);
        address = (EditText) findViewById(R.id.etAddress);
        cancel= (Button) findViewById(R.id.bCancel);
        update= (Button) findViewById(R.id.bUpdate);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(editProfile.this, cookhomeActivity.class);
                startActivity(i);

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(editProfile.this, cookhomeActivity.class);
                startActivity(i);

            }
        });



    }
    public boolean profileCheck(){
        final AlertDialog.Builder sets = new AlertDialog.Builder(this);
        sets.setTitle("Error");
        sets.setIcon(android.R.drawable.btn_star_big_on);
        sets.setPositiveButton("Close", null);
        if (username.getText().length() == 0) {
            sets.setMessage("Please input[name]");
            sets.show();
            username.requestFocus();
            return false;

        }


        if (address.getText().length() == 0) {
            sets.setMessage("Please input address");
            sets.show();
            address.requestFocus();
            return false;

        }
        if (phone.getText().length() == 0) {
            sets.setMessage("Please input phone");
            sets.show();
            phone.requestFocus();
            return false;

        }

        else{
            Intent i=new Intent(getApplicationContext(),profileActivity.class);
            startActivity(i);
        }
        return true;
    }


}
