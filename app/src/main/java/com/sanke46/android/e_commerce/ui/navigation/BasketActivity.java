package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.adapter.ListAdapterBasket;
import com.sanke46.android.e_commerce.model.Item;
import com.sanke46.android.e_commerce.model.ItemBasket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketActivity extends AppCompatActivity {

    public static List<Item> basketItem = new ArrayList<Item>();
    public static List<ItemBasket> basketItemBasket = new ArrayList<ItemBasket>();
    public static HashMap<Integer, Item> mapBasketItem = new HashMap();
    public ListAdapterBasket basketAdapter;
    private int sum;
    private ListView listView;
    private Button buttonOrder;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBasket);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        setSupportActionBar(toolbar);
//        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.bgWhite), PorterDuff.Mode.SRC_ATOP);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_go_back_left_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                startActivity(intent);
                finish();
                onBackPressed();

            }
        });

        buttonOrder = (Button) findViewById(R.id.priceButton);

        refreshTotalPrice();
        sortBasketList();
        addToList();

        listView = (ListView) findViewById(R.id.basketView);
        basketAdapter = new ListAdapterBasket(this, basketItemBasket);
        listView.setAdapter(basketAdapter);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sum <= 0) {
                    Toast.makeText(BasketActivity.this, "You haven't got any eat", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(BasketActivity.this, OrderActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });

    }



    public List<Item> getBasketItem() {
        return basketItem;
    }

    public void setBasketItem(List<Item> basketItem) {
        this.basketItem = basketItem;
    }

    public void refreshUi() {
        listView.setAdapter(basketAdapter);
        refreshTotalPrice();
    }

    private void refreshTotalPrice() {
        sum = 0;
        for (int i = 0; i < basketItem.size(); i++) {
            if (basketItem.get(i).isSales()) {
                sum += basketItem.get(i).getDiscontPrice();
            } else {
                sum += basketItem.get(i).getPrice();
            }
        }

        buttonOrder.setText("ORDER - " + sum + " $");
    }

    public void sortBasketList() {
        mapBasketItem = new HashMap<>();
        for (Item item : basketItem) {
            int count = 0;
            for (Item item2 : basketItem) {
                if(item.equals(item2)) count++;
            }
            mapBasketItem.put(count, item);
            System.out.print(item);
        }

        System.out.println(basketItem.size());
        System.out.println(mapBasketItem);
    }

    public void addToList() {
        basketItemBasket = new ArrayList<>();
        for (Map.Entry<Integer, Item> entry : mapBasketItem.entrySet()) {
            basketItemBasket.add(new ItemBasket(entry.getKey(), entry.getValue()));
            System.out.println(basketItemBasket.size() + "basket");
        }
    }

}
