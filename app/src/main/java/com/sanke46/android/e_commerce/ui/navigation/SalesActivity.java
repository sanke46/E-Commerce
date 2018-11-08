package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.sanke46.android.e_commerce.MainActivity;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.ViewModel.SalesActivityViewModel;
import com.sanke46.android.e_commerce.adapter.RecyclerViewAdapter;
import com.sanke46.android.e_commerce.adapter.SalesRecyclerViewAdapter;

public class SalesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SalesActivityViewModel saleViewModel = new SalesActivityViewModel();
    private SalesRecyclerViewAdapter mSalesRecycleViewAdapter;

    // First RecycleView
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    private DrawerLayout drawer;


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

        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);

//        progressBar = findViewById(R.id.salesProgressBar);
        linearLayout = findViewById(R.id.salesLinearLayout);

        // All products [RecycleView + Adapter + LayoutManager + FB]
        mRecyclerView = findViewById(R.id.sales_list);
        mLayoutManager = new GridLayoutManager(this,2);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.catalog) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.sales) {
//            Intent intent = new Intent(this, SalesActivity.class);
//            startActivity(intent);
        } else if (id == R.id.order) {
            Intent intent = new Intent(this, BasketActivity.class);
            startActivity(intent);
        } else if (id == R.id.about) {
            Intent intent = new Intent(this, AboutDelevery.class);
            startActivity(intent);
        } else if (id == R.id.profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }else if (id == R.id.sing_out) {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.setting) {
            startActivity(new Intent(this, SettingActivity.class));
        } else if (id == R.id.chat) {
            startActivity(new Intent(this, ChatActivity.class));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
