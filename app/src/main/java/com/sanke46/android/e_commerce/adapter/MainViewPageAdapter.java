package com.sanke46.android.e_commerce.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sanke46.android.e_commerce.ui.orderable.Drinks;
import com.sanke46.android.e_commerce.ui.orderable.Pizza;
import com.sanke46.android.e_commerce.ui.orderable.Sushi;

/**
 * Created by ilafedoseev on 05.02.17.
 */

public class MainViewPageAdapter extends FragmentPagerAdapter {

    public MainViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0 ){
            return new Pizza();
        } else if(position == 1) {
            return new Sushi();
        } else {
            return  new Drinks();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
