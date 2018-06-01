package com.sanke46.android.e_commerce.ui.orderable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.adapter.RecyclerViewAdapter;
import com.sanke46.android.e_commerce.adapter.SalesRecyclerViewAdapter;
import com.sanke46.android.e_commerce.fireBase.FirebaseHandler;
import com.sanke46.android.e_commerce.model.Item;

import java.util.ArrayList;

public class Drinks extends Fragment {

    private static final String PRODUCT_CATEGORY_ID = "drinks";
    private static final String TAG = Drinks.class.getSimpleName();

    private final ArrayList<Item> allDrinksItems = new ArrayList<>();
    private final ArrayList<Item> allDiscountDrinksItems = new ArrayList<>();

    // First RecycleView
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    // Second RecycleView
    private RecyclerView.LayoutManager mSaleLayoutManager;
    private RecyclerView mSalerecycleView;
    private SalesRecyclerViewAdapter mSalesRecycleViewAdapter;

    private ProgressBar progressBar;
    private LinearLayout mContentLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview_3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        FirebaseHandler fb = new FirebaseHandler();

        // All products [RecycleView + Adapter + LayoutManager + FB]
        mRecyclerView = view.findViewById(R.id.list_3);
        mLayoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(allDrinksItems, getContext());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        fb.getAllItem(PRODUCT_CATEGORY_ID, allDrinksItems, mRecyclerViewAdapter);

        // Progress bar + LinerLayout content
        progressBar = view.findViewById(R.id.progress_bar_three);
        mContentLayout = view.findViewById(R.id.content_tree);

        // Discount [RecycleView + Adapter + LayoutManager + FB]
        mSalerecycleView = view.findViewById(R.id.list_sale_3);
        mSaleLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mSalerecycleView.setLayoutManager(mSaleLayoutManager);
        mSalesRecycleViewAdapter = new SalesRecyclerViewAdapter(getContext(), allDiscountDrinksItems);
        mSalerecycleView.setAdapter(mSalesRecycleViewAdapter);
        mSalerecycleView.setNestedScrollingEnabled(false);
        fb.getAllSalesItem(PRODUCT_CATEGORY_ID, allDiscountDrinksItems, mSalesRecycleViewAdapter, progressBar, mContentLayout);

    }
}
