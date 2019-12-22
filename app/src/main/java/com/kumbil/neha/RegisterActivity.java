package com.kumbil.neha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    Button Cook,Delivery,Buyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Cook = (Button) findViewById(R.id.bCook);
        Delivery = (Button) findViewById(R.id.bDelivery);
        Buyer = (Button) findViewById(R.id.bBuyer);
        Cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, loginActivity.class);
                startActivity(i);

            }
        });
        Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, loginActivity.class);
                startActivity(i);

            }

        });

        Buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, loginActivity.class);
                startActivity(i);

            }

        });


    }
    }

