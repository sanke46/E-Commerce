package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.adapter.ListInformationAdapter;
import com.sanke46.android.e_commerce.model.InfoDetail;
import com.sanke46.android.e_commerce.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private BasketActivity basketActivity = new BasketActivity();
    private List<Item> itemList = basketActivity.getBasketItem();
    private ListInformationAdapter listAdapter;
    private List<InfoDetail> info = new ArrayList<>();
    private Item item;
    Intent intentItem;

    private ImageView image;
    private TextView name;
    private ImageView spicy;
    private ImageView vegan;
    private TextView price;
    private LinearLayout isSales;
    private TextView priceMain;
    private TextView priceSales;
    private TextView information;
    private TextView minus;
    private TextView howMany;
    private TextView plus;
    private Button addToCart;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_36px));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        name = findViewById(R.id.name_detail);
        image = findViewById(R.id.image_id);
        spicy = findViewById(R.id.spicy);
        vegan = findViewById(R.id.vegan);
        price = findViewById(R.id.price);
        isSales = findViewById(R.id.sales);
        priceMain = findViewById(R.id.priceMain);
        priceSales = findViewById(R.id.salesPrice);
        information = findViewById(R.id.info_item);
        minus = findViewById(R.id.minus);
        howMany = findViewById(R.id.howMany);
        plus = findViewById(R.id.plus);
        addToCart = findViewById(R.id.addToCart);
        listView = findViewById(R.id.allInformation);

        intentItem = getIntent();
        item = (Item) intentItem.getSerializableExtra("item");
        addInfoToList();
        listAdapter = new ListInformationAdapter(getApplicationContext(), info);
        listView.setAdapter(listAdapter);

        name.setText(item.getName());
        Picasso.with(getApplicationContext()).load(item.getImageUrl()).into(image);
        spicy.setVisibility(item.isSpice() ? View.VISIBLE : View.GONE);
        vegan.setVisibility(item.isVegetarian() ? View.VISIBLE : View.GONE);
        price.setText(String.valueOf(item.getPrice() + " $"));
        displaySales();
        information.setText(item.getComment());
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               howMany.setText(String.valueOf(Integer.parseInt((String) howMany.getText()) - 1));
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                howMany.setText(String.valueOf(Integer.parseInt((String) howMany.getText()) + 1));
            }
        });
      addToCart.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(howManyToInt() == 0) {
                  itemList.add(item);
                  basketActivity.setBasketItem(itemList);
              } else {
                  for (int i = 0; i < howManyToInt(); i++) {
                      itemList.add(item);
                      basketActivity.setBasketItem(itemList);
                  }
              }
          }
      });
    }

    private void displaySales() {
        if (item.isSales()) {
            isSales.setVisibility(View.VISIBLE);
            priceMain.setText(item.getPrice() + " $");
            priceMain.setPaintFlags(priceMain.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            priceSales.setText(item.getDiscontPrice() + " $");
            // hide price TextView
            price.setVisibility(View.GONE);
        } else {
            price.setVisibility(View.VISIBLE);
            price.setText(item.getPrice() + " $");
            // hide sales LinerLayout
            isSales.setVisibility(View.GONE);
        }
    }

    private Integer howManyToInt() {
        return Integer.parseInt(String.valueOf(howMany.getText()));
    }

    private void addInfoToList() {
        info.add(new InfoDetail("Gramms", String.valueOf(item.getGramms())));
        info.add(new InfoDetail("Kalories", String.valueOf(item.getKalories())));
        info.add(new InfoDetail("Protein", String.valueOf(item.getProtein())));
        info.add(new InfoDetail("Carb", String.valueOf(item.getCarbohydrates())));
        info.add(new InfoDetail("Status", item.getStatus()));
        info.add(new InfoDetail("ComeFrom", item.getComeFrom()));
    }

}