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

import com.sanke46.android.e_commerce.adapter.ListAdapterBasket;
import com.sanke46.android.e_commerce.MainActivity;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.model.Item;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {

    public static List<Item> basketItem = new ArrayList<Item>();
    public ListAdapterBasket basketAdapter;
    private int sum;
    private ListView listView;
    private Button buttonOrder;
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

        buttonOrder = (Button) findViewById(R.id.priceButton);
        refreshTotalPrice();

//        String[] dummyStrings = getResources().getStringArray(R.array.my_items);
        listView = (ListView) findViewById(R.id.basketView);
        basketAdapter = new ListAdapterBasket(this, basketItem);
        listView.setAdapter(basketAdapter);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sum  <= 0){
                    Toast.makeText(BasketActivity.this, "You haven't got any eat", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(BasketActivity.this, OrderActivity.class);
                    startActivity(intent);
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

    private List<Item> sortBasketToMap(List<Item> arrayOfItem) {
        for (Item item : arrayOfItem) {

        }
        return null;
    }
}
