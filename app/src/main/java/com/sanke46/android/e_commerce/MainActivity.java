package com.sanke46.android.e_commerce;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TabHost;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.piz_catalog).withIcon(FontAwesome.Icon.faw_list),
                        new PrimaryDrawerItem().withName(R.string.sales).withIcon(FontAwesome.Icon.faw_money),
                        new PrimaryDrawerItem().withName(R.string.my_order).withIcon(FontAwesome.Icon.faw_shopping_cart),
                        new PrimaryDrawerItem().withName(R.string.profile).withIcon(FontAwesome.Icon.faw_user),
                        new PrimaryDrawerItem().withName(R.string.sing_out).withIcon(FontAwesome.Icon.faw_sign_out)
                )
                .build();
    }

    public class mainActivityTwo extends TabActivity {

        MainActivity mainActivityTwo = new MainActivity();
        private static final String FIRST_TAB = "One";
        private static final String SECOND_TAB = "Two";
        private static final String THIRD_TAB = "Tree";


        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            TabHost tabHost = getTabHost();
            TabHost.TabSpec oneSpace = tabHost.newTabSpec(FIRST_TAB);

            oneSpace.setIndicator(FIRST_TAB,getResources().getDrawable(R.drawable.ic_launcher));
            Intent firtsIntent = new Intent(this,FirstActivityList.class);

            oneSpace.setContent(firtsIntent);
        }

    }
}
