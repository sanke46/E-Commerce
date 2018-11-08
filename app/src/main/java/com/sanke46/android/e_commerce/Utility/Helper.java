package com.sanke46.android.e_commerce.Utility;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class Helper {
    private Context context;

    public Helper(Context context) {
        this.context = context;
    }

    public void actionSnackBar(View view, String text) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public void doneLoadingActivity(View toShow, View toHide) {
        toShow.setVisibility(View.VISIBLE);
        toHide.setVisibility(View.GONE);
    }

    public void isEmpty(Integer listOfItem, View visible, View hide) {
        if(listOfItem != 0) {
            doneLoadingActivity(visible, hide);
        } else {
            doneLoadingActivity(hide, visible);
        }
    }
}
