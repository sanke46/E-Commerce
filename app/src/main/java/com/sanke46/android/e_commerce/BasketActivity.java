package com.sanke46.android.e_commerce;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    public static List<Integer> cart = new ArrayList<>();

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

        DataBaseHandler db = new DataBaseHandler(this);
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < cart.size(); i++) {
            itemList.add(db.getItem(cart.get(i),"Pi"));
        }

        ListView listView = (ListView) findViewById(R.id.listViewCart);
        ListAdapter listA = new ListAdapter(this,itemList);

    }



    public static List<Integer> getCart() {
        return cart;
    }

    public static void setCart(List<Integer> cart) {
        BasketActivity.cart = cart;
    }
}
