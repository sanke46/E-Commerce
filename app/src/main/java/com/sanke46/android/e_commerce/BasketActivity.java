package com.sanke46.android.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {

    public static List<Item> basketItem = new ArrayList<Item>();
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_36px));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        
        String[] dummyStrings = getResources().getStringArray(R.array.my_items);
        ListView listView = (ListView) findViewById(R.id.basketView);
        ListAdapter basketAdapter = new ListAdapter(this,basketItem);
        listView.setAdapter(basketAdapter);
    }

    public List<Item> getBasketItem() {
        return basketItem;
    }

    public void setBasketItem(List<Item> basketItem) {
        this.basketItem = basketItem;
    }
}