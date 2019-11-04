package com.kumbil.neha;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register2_Activity extends AppCompatActivity {
    EditText username,password,place,email,phoneNo;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2_);
        username=(EditText)findViewById(R.id.etUsername);
        password=(EditText)findViewById(R.id.etPassword);
        place=(EditText)findViewById(R.id.etPlace);
        email=(EditText)findViewById(R.id.etEmail);
        phoneNo=(EditText)findViewById(R.id.etPhone);
        Register=(Button)findViewById(R.id.bRegister);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RegisterCheck()) {

                }
                else
                {
                    Toast.makeText(Register2_Activity.this, "failed", Toast.LENGTH_SHORT).show();
                }

                }
        });


    }
    public boolean RegisterCheck(){
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
            sets.setMessage("Please input[phone]");
            sets.show();
            password.requestFocus();
            return false;

        }
        if (place.getText().length() == 0) {
            sets.setMessage("Please input[place]");
            sets.show();
            place.requestFocus();
            return false;

        }
        if (phoneNo.getText().length() == 0) {
            sets.setMessage("Please input[phone]");
            sets.show();
            phoneNo.requestFocus();
            return false;

        }
        else{
            Intent i=new Intent(getApplicationContext(),cookhomeActivity.class);
            startActivity(i);
        }
        return true;
    }
}
