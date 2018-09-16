package com.sanke46.android.e_commerce.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sanke46.android.e_commerce.ui.navigation.ProfilePages.ProfileHistoryActivity;
import com.sanke46.android.e_commerce.ui.navigation.ProfilePages.ProfileInfoActivity;
import com.sanke46.android.e_commerce.ui.orderable.Drinks;
import com.sanke46.android.e_commerce.ui.orderable.Pizza;
import com.sanke46.android.e_commerce.ui.orderable.Sushi;

/**
 * Created by ilafedoseev on 05.02.17.
 */

public class ProfileViewPageAdapter extends FragmentPagerAdapter {


    public ProfileViewPageAdapter(Context context, FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0 ){
            return new ProfileInfoActivity();
        } else {
            return  new ProfileHistoryActivity();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
