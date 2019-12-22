package com.kumbil.neha;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Register2_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText username,password,place,email,phoneNo;
    Button Register;
    Spinner type;
    String []typedata={"Cook","Delivery","Customer"};

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
        type=(Spinner)findViewById(R.id.type);
        ArrayAdapter spindata=new ArrayAdapter(this,android.R.layout.simple_spinner_item,typedata);
        spindata.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(spindata);
        type.setOnItemSelectedListener(this);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RegisterCheck()) {
                    Toast.makeText(Register2_Activity.this,"Register success",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Register2_Activity.this,cookhomeActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(Register2_Activity.this, "failed", Toast.LENGTH_SHORT).show();
                }

                }
        });


    }
    public boolean RegisterCheck(){
        Log.i("type", "@registercheck");
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
            sets.setMessage("Please input password");
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
        if (email.getText().length() == 0) {
            sets.setMessage("Please input email");
            sets.show();
            email.requestFocus();
            return false;

        }
        if (phoneNo.getText().length() == 0) {
            sets.setMessage("Please input[phone]");
            sets.show();
            phoneNo.requestFocus();
            return false;

        }

        String userType = type.getSelectedItem().toString();

        Log.i("type", userType);

        String url="http://192.168.225.39/kumbil/register.php";

        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username",username.getText().toString()));
        params.add(new BasicNameValuePair("password",password.getText().toString()));
        params.add(new BasicNameValuePair("place",place.getText().toString()));
        params.add(new BasicNameValuePair("email",email.getText().toString()));
        params.add(new BasicNameValuePair("phone",phoneNo.getText().toString()));
        params.add(new BasicNameValuePair("type",userType));
        Log.e("type", "@registercheck - b");
        String resultServer=getHttpPost(url,params);
        String strStatusId="1";
        String strError="Invalid Registration";
        JSONObject C;
        try
        {
            C=new JSONObject(resultServer);
            strStatusId=C.getString("status");
            strError=C.getString("message");

        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        if(!strStatusId.equals("0"))
        {
            sets.setMessage(strError);
            sets.show();
            return false;
        }
        else {
            return true;
        }

    }
    public String getHttpPost(String url, List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        Log.i("type", "@getHTTP_POST");

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine;
            statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
                Log.i("type", line);
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
