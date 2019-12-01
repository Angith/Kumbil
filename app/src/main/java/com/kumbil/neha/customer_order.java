package com.kumbil.neha;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class customer_order extends AppCompatActivity {
EditText deliveryTo,qty,time;
Button cancel,buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_order);
        deliveryTo = (EditText) findViewById(R.id.etdelivery);
        qty= (EditText) findViewById(R.id.etqty);
        time= (EditText) findViewById(R.id.etTime);
        cancel= (Button) findViewById(R.id.bCancel);
        buy= (Button) findViewById(R.id.bRegister);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(OrderCheck()) {

                }
                else
                {
                    Toast.makeText(customer_order.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(customer_order.this,CustomerActivity.class);
                startActivity(i);
            }
        });

    }
    public boolean OrderCheck(){
        final AlertDialog.Builder sets = new AlertDialog.Builder(this);
        sets.setTitle("Error");
        sets.setIcon(android.R.drawable.btn_star_big_on);
        sets.setPositiveButton("Close", null);
        if (deliveryTo.getText().length() == 0) {
            sets.setMessage("Please input[name]");
            sets.show();
            deliveryTo.requestFocus();
            return false;

        }


        if (qty.getText().length() == 0) {
            sets.setMessage("Please input[password]");
            sets.show();
            qty.requestFocus();
            return false;

        }
        if (time.getText().length() == 0) {
            sets.setMessage("Please input[password]");
            sets.show();
            time.requestFocus();
            return false;

        }
        String url="https://www.grapestechs.com/BCA_Disaster/register.php";

        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Sdelivery",deliveryTo.getText().toString()));
        params.add(new BasicNameValuePair("Sqty",qty.getText().toString()));
        params.add(new BasicNameValuePair("Stime",time.getText().toString()));


        String resultServer=getHttpPost(url,params);
        String strStatusId="0";
        String strError="Invalid order";
        JSONObject C;
        try
        {
            C=new JSONObject(resultServer);
            strStatusId=C.getString("StatusID");
            strError=C.getString("Error");

        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        if(strStatusId.equals("0"))
        {
            sets.setMessage(strError);
            sets.show();
            return false;
        }
        else {
            Toast.makeText(this,"login success",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(customer_order.this,CustomerActivity.class);
            startActivity(i);
        }
        return true;
    }
    public String getHttpPost(String url, List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);


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
}




