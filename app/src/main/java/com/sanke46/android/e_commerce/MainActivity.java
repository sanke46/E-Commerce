package com.sanke46.android.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.sanke46.android.e_commerce.adapter.ViewPagerAdapter;
import com.sanke46.android.e_commerce.database.DataBaseHandler;
import com.sanke46.android.e_commerce.model.Item;
import com.sanke46.android.e_commerce.ui.navigation.AboutDelevery;
import com.sanke46.android.e_commerce.ui.navigation.BasketActivity;
import com.sanke46.android.e_commerce.ui.navigation.LoginActivity;
import com.sanke46.android.e_commerce.ui.navigation.ProfileActivity;
import com.sanke46.android.e_commerce.ui.navigation.SalesActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private String[] pageTitle = {"Pizza", "Sushi", "Drinks"};
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);

        DataBaseHandler db = new DataBaseHandler(this);

//        // Inserting Contacts

        db.addItem("Su",new Item(R.drawable.sushi,"Sushi1","rice, cucumber, fish, soy",11,"Button1","+"));
        db.addItem("Su",new Item(R.drawable.sushi2,"Sushi2","rice, cucumber, fish, soy",12,"Button1","+"));
        db.addItem("Su",new Item(R.drawable.sushi3,"Sushi3","rice, cucumber, fish, soy",13,"Button1","+"));
        db.addItem("Pi", new Item(R.drawable.pizza,"Pizza1","tomatoes, onions, olives, cheese, chicken",19,"Button1","+"));
        db.addItem("Pi", new Item(R.drawable.pizza2,"Pizza2","tomatoes, onions, olives, cheese, chicken",20,"Button1","+"));
        db.addItem("Pi", new Item(R.drawable.pizza3,"Pizza3","tomatoes, onions, olives, cheese, chicken",21,"Button1","+"));
        db.addItem("Dr", new Item(R.drawable.drinks,"Coca-cola","tomatoes, onions, olives, cheese, chicken",22,"Button1","+"));
        db.addItem("Dr", new Item(R.drawable.drinks2,"Pepsi","Pepsi is a carbonated soft drink produced and manufactured by PepsiCo",23,"Button1","+"));
        db.addItem("Dr", new Item(R.drawable.drinks3,"Sprite","tomatoes, onions, olives, cheese, chicken",24,"Button1","+"));
        List<Item> list = db.getAllItem("Su");
        List<Item> list2 = db.getAllItem("Pi");
        List<Item> list3 = db.getAllItem("Dr");

//        for (int i = 0; i < 3; i++) {
//            System.out.println(list.get(i));
//            System.out.println(list2.get(i));
//            System.out.println(list3.get(i));
//        }

//        db.deleteAll("Su");
//        db.deleteAll("Pi");
//        db.deleteAll("Dr");
//        System.out.println("DONE");

        // Test of login user or not
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {
            Log.v("MainActivity", "USER not already login");
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            Log.v("MainActivity", "User already login ");
        }

//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                } else {
//                    // User is signed out
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                }
//            }
//        };

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < 3; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        View hView =  navigationView.getHeaderView(0);
//        TextView nav_user = (TextView)hView.findViewById(R.id.nav_name);
//        nav_user.setText(user);

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //change ViewPager page when tab selected
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.catalog) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.sales) {
            Intent intent = new Intent(this, SalesActivity.class);
            startActivity(intent);
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
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    @Override
    public void onBackPressed() {
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
