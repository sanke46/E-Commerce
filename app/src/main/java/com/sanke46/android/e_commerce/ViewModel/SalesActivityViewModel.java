package com.sanke46.android.e_commerce.ViewModel;

import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sanke46.android.e_commerce.adapter.SalesRecyclerViewAdapter;
import com.sanke46.android.e_commerce.fireBase.FirebaseHandler;
import com.sanke46.android.e_commerce.model.Item;

import java.util.ArrayList;

public class SalesActivityViewModel {

    private static final String PRODUCT_CATEGORY_ID = "pizza";
    private static final String PRODUCT_CATEGORY_ID_2 = "sushi";
    private static final String PRODUCT_CATEGORY_ID_3 = "drinks";

    public final ArrayList<Item> allSalesItems = new ArrayList<>();
    FirebaseHandler firebaseHandler = new FirebaseHandler();

    public void fireBaseProductsToList(SalesRecyclerViewAdapter adapter, ProgressBar bar, LinearLayout linearLayout) {
        firebaseHandler.getAllSalesItem(PRODUCT_CATEGORY_ID, allSalesItems, adapter, bar, linearLayout);
        firebaseHandler.getAllSalesItem(PRODUCT_CATEGORY_ID_2, allSalesItems, adapter, bar, linearLayout);
        firebaseHandler.getAllSalesItem(PRODUCT_CATEGORY_ID_3, allSalesItems, adapter, bar, linearLayout);
    }
}
