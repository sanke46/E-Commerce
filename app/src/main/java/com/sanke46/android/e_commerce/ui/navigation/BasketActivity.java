package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.ViewModel.BasketActivityViewModel;
import com.sanke46.android.e_commerce.adapter.ListAdapterBasket;

public class BasketActivity extends AppCompatActivity {

    private static BasketActivityViewModel basketViewModel;
    public ListAdapterBasket basketAdapter;
    private ListView listView;
    private Button buttonOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        buttonOrder = findViewById(R.id.priceButton);
        Toolbar toolbar = findViewById(R.id.toolbarBasket);
        basketViewModel = new BasketActivityViewModel();
        listView = findViewById(R.id.basketView);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_go_back_left_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });

        refreshUi();

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (basketViewModel.sum <= 0) {
                    Toast.makeText(BasketActivity.this, "You haven't got any eat", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(new Intent(BasketActivity.this, OrderActivity.class));
                    finish();
                }
            }
        });
    }

    public void refreshUi() {
        basketViewModel.sortListToMap();
        basketViewModel.sortMapToList();
        basketViewModel.refreshTotalPrice(buttonOrder);

        basketAdapter = new ListAdapterBasket(this, basketViewModel.basketItemBasket);
        listView.setAdapter(basketAdapter);
    }





}
