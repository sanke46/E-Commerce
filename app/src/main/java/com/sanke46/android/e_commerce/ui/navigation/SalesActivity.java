package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sanke46.android.e_commerce.MainActivity;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.ViewModel.SalesActivityViewModel;
import com.sanke46.android.e_commerce.adapter.RecyclerViewAdapter;
import com.sanke46.android.e_commerce.adapter.SalesRecyclerViewAdapter;

public class SalesActivity extends AppCompatActivity {

    private SalesActivityViewModel saleViewModel = new SalesActivityViewModel();
    private SalesRecyclerViewAdapter mSalesRecycleViewAdapter;

    // First RecycleView
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        Toolbar toolbar = findViewById(R.id.toolbar);
//        getSupportActionBar().setTitle("Sales");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_go_back_left_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        progressBar = findViewById(R.id.salesProgressBar);
        linearLayout = findViewById(R.id.salesLinearLayout);

        // All products [RecycleView + Adapter + LayoutManager + FB]
        mRecyclerView = findViewById(R.id.sales_list);
        mLayoutManager = new GridLayoutManager(this,1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(saleViewModel.allSalesItems, this);
        mSalesRecycleViewAdapter = new SalesRecyclerViewAdapter(this, saleViewModel.allSalesItems,  R.layout.item_sales_activity);
        mRecyclerView.setAdapter(mSalesRecycleViewAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);

        saleViewModel.fireBaseProductsToList(mSalesRecycleViewAdapter,progressBar,linearLayout);
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
                startActivity(new Intent(this, BasketActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
