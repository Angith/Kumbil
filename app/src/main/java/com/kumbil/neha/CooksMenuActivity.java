package com.kumbil.neha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class CooksMenuActivity extends AppCompatActivity {

    private ArrayList<Menu> menuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooks_menu);
        RecyclerView menus = (RecyclerView) findViewById(R.id.menu_rv);
        menus.setLayoutManager(new LinearLayoutManager(this));
        menus.setAdapter(new MenuRVAdapter(menuItems, this));
        menuItems.add(new Menu("Biriyani", "120", "Kumbil special biriyani"));
        menuItems.add(new Menu("Alpham Manthi", "600", "Kumbil special manthi"));
        menuItems.add(new Menu("Kuzhi Manthi", "600", "Kumbil special manthi"));
    }
}
