package com.kumbil.neha;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    EditText username,password;
    Button login,createnewaccount;
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.etUsername);
        password= (EditText) findViewById(R.id.etPassword);
        login= (Button) findViewById(R.id.bLogin);
        createnewaccount= (Button) findViewById(R.id.bCreateAccount);
        forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginCheck()) {

                }
                else
                {
                    Toast.makeText(loginActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        createnewaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(loginActivity.this,Register2_Activity.class);
                startActivity(i);
            }
        });

        }
        public boolean LoginCheck(){
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


            if (password.getText().length() == 0) {
                sets.setMessage("Please input[password]");
                sets.show();
                password.requestFocus();
                return false;

            }
            else{
                Intent i=new Intent(getApplicationContext(),cookhomeActivity.class);
                startActivity(i);
            }
            return true;
        }



    }

