package com.kumbil.neha;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

        String url="http://192.168.225.39/kumbil/login.php";

        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Sname",username.getText().toString()));
        params.add(new BasicNameValuePair("Spassword",password.getText().toString()));

        String resultServer=getHttpPost(url,params);
        String strStatusId="0";
        String strError="Invalid Registration";
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
            Intent i=new Intent(loginActivity.this,cookhomeActivity.class);
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
