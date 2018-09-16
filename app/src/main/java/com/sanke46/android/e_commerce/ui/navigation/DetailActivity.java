package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.podcopic.animationlib.library.AnimationType;
import com.podcopic.animationlib.library.StartSmartAnimation;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.Utility.Helper;
import com.sanke46.android.e_commerce.ViewModel.DetailActivityViewModel;
import com.sanke46.android.e_commerce.adapter.ListInformationAdapter;
import com.sanke46.android.e_commerce.model.Item;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private DetailActivityViewModel detailViewModel;
    private ListInformationAdapter listAdapter;
    private Helper helper = new Helper(this);

    private ImageView image;
    private TextView name;
    private ImageView spicy;
    private ImageView vegan;
    private TextView isNotSales;
    private LinearLayout isSales;
    private TextView priceMain;
    private TextView priceSales;
    private TextView information;
    private TextView minus;
    private TextView howMany;
    private TextView plus;
    private Button btn;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_go_back_left_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // get info about this product
        detailViewModel = new DetailActivityViewModel((Item) getIntent().getSerializableExtra("item"));

        name = findViewById(R.id.name_detail);
        image = findViewById(R.id.image_id);
        spicy = findViewById(R.id.spicy);
        vegan = findViewById(R.id.vegan);
        isNotSales = findViewById(R.id.price);
        isSales = findViewById(R.id.sales);
        priceMain = findViewById(R.id.priceMain);
        priceSales = findViewById(R.id.salesPrice);
        information = findViewById(R.id.info_item);
        minus = findViewById(R.id.minus);
        howMany = findViewById(R.id.howMany);
        plus = findViewById(R.id.plus);
        btn = findViewById(R.id.btn);
        listView = findViewById(R.id.allInformation);

        // add main information about product
        name.setText(detailViewModel.item.getName());
        Picasso.with(getApplicationContext()).load(detailViewModel.item.getImageUrl()).into(image);
        spicy.setVisibility(detailViewModel.item.isSpice() ? View.VISIBLE : View.GONE);
        vegan.setVisibility(detailViewModel.item.isVegetarian() ? View.VISIBLE : View.GONE);
        isNotSales.setText(String.valueOf(detailViewModel.item.getPrice() + " $"));
        displayNormalOrSalesPrice();
        information.setText(detailViewModel.item.getComment());

        // realize ListView information
        listView.setFocusable(false);
        listAdapter = new ListInformationAdapter(getApplicationContext(),  detailViewModel.getListOfProductInfo());
        listView.setAdapter(listAdapter);

        // BUTTON [minus]
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!howMany.getText().equals("1")) {
                    howMany.setText(detailViewModel.minusCountProductToBasket());
                }
            }
        });

        // BUTTON [plus]
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                howMany.setText(detailViewModel.plusCountProductToBasket());
            }
        });

        // BUTTON [add to cart]
        btn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  StartSmartAnimation.startAnimation(btn, AnimationType.FadeOut, 600, 0, false);
                  StartSmartAnimation.startAnimation(btn, AnimationType.FadeIn, 600, 900, false);
                  detailViewModel.addProductToBasket(howMany);
                  helper.actionSnackBar(view, "add to basket: " + howMany.getText());
                  detailViewModel.resetCountProductsToBasket();
                  howMany.setText("1");
              }
          });
    }

    private void displayNormalOrSalesPrice() {
      if (detailViewModel.item.isSales()) {
          isSales.setVisibility(View.VISIBLE);
          isNotSales.setVisibility(View.GONE);
          priceMain.setText(detailViewModel.item.getPrice() + " $");
          priceSales.setText(detailViewModel.item.getDiscontPrice() + " $");
          priceMain.setPaintFlags(priceMain.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
      } else {
          isSales.setVisibility(View.GONE);
          isNotSales.setVisibility(View.VISIBLE);
          isNotSales.setText(detailViewModel.item.getPrice() + " $");
      }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.basket_button:
                Intent intent = new Intent(this, BasketActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}