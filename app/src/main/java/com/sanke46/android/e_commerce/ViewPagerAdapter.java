package com.sanke46.android.e_commerce;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ilafedoseev on 05.02.17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0 ){
            return new Fragment1();
        } else if(position == 1) {
            return new Fregment2();
        } else {
            return  new Fragment3();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
