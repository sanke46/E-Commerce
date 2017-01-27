package com.sanke46.android.e_commerce;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by ilafedoseev on 27.01.17.
 */

public class SecondActivityList extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.second_list_item);
    }
}
