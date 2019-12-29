package com.kumbil.neha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class CooksMenuActivity extends AppCompatActivity {

    private ArrayList<CookMenu> cookMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooks_menu);
//        RecyclerView menus = (RecyclerView) findViewById(R.id.menu_rv);
//        menus.setLayoutManager(new LinearLayoutManager(this));
//        menus.setAdapter(new MenuRVAdapter(cookMenuItems, this));
//        cookMenuItems.add(new CookMenu("Biriyani", "120", "Kumbil special biriyani"));
//        cookMenuItems.add(new CookMenu("Alpham Manthi", "600", "Kumbil special manthi"));
//        cookMenuItems.add(new CookMenu("Kuzhi Manthi", "600", "Kumbil special manthi"));
    }
}
