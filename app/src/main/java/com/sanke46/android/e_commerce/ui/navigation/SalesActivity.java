package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.sanke46.android.e_commerce.MainActivity;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.adapter.RecyclerViewAdapter;
import com.sanke46.android.e_commerce.adapter.SalesRecyclerViewAdapter;
import com.sanke46.android.e_commerce.fireBase.FirebaseHandler;
import com.sanke46.android.e_commerce.model.Item;

import java.util.ArrayList;

public class SalesActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private static final String PRODUCT_CATEGORY_ID = "pizza";
    private final ArrayList<Item> allSalesItems = new ArrayList<>();
    private SalesRecyclerViewAdapter mSalesRecycleViewAdapter;
    // First RecycleView
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
//        progressBar = findViewById(R.id.progress_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sales");
        FirebaseHandler fb = new FirebaseHandler();

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_36px));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // All products [RecycleView + Adapter + LayoutManager + FB]
        mRecyclerView = (RecyclerView) findViewById(R.id.sales_list);
        mLayoutManager = new GridLayoutManager(this,1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(allSalesItems, this);
        mSalesRecycleViewAdapter = new SalesRecyclerViewAdapter(this, allSalesItems);
        mRecyclerView.setAdapter(mSalesRecycleViewAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
//        fb.getAllItem(PRODUCT_CATEGORY_ID, allSalesItems, mRecyclerViewAdapter);
//        fb.getAllSalesItem(PRODUCT_CATEGORY_ID, allSalesItems,mSalesRecycleViewAdapter,progressBar);
    }


}
