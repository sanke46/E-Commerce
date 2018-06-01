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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.adapter.RecyclerViewAdapter;
import com.sanke46.android.e_commerce.adapter.SalesRecyclerViewAdapter;
import com.sanke46.android.e_commerce.fireBase.FirebaseHandler;
import com.sanke46.android.e_commerce.model.Item;

import java.util.ArrayList;

public class Pizza extends Fragment {

    private static final String PRODUCT_CATEGORY_ID = "pizza";
    private static final String TAG = Pizza.class.getSimpleName();

    private final ArrayList<Item> allPizzaItems = new ArrayList<>();
    private final ArrayList<Item> allDiscountPizzaItems = new ArrayList<>();

    // First RecycleView
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;

    // Second RecycleView
    private RecyclerView.LayoutManager mSaleLayoutManager;
    private RecyclerView mSaleRecycleView;
    private SalesRecyclerViewAdapter mSalesRecycleViewAdapter;

    private ProgressBar progressBar;
    private LinearLayout mContentLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview_1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        FirebaseHandler fb = new FirebaseHandler();

        // All products [RecycleView + Adapter + LayoutManager + FB]
        mRecyclerView = view.findViewById(R.id.list_1);
        mLayoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(allPizzaItems, getContext());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        fb.getAllItem(PRODUCT_CATEGORY_ID, allPizzaItems, mRecyclerViewAdapter);

        // Progress bar
        progressBar = view.findViewById(R.id.progress_bar_one);
        mContentLayout = view.findViewById(R.id.content_one);

        // Discount [RecycleView + Adapter + LayoutManager + FB]
        mSaleRecycleView = view.findViewById(R.id.list_sale);
        mSaleLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mSaleRecycleView.setLayoutManager(mSaleLayoutManager);
        mSalesRecycleViewAdapter = new SalesRecyclerViewAdapter(getContext(), allDiscountPizzaItems);
        mSaleRecycleView.setAdapter(mSalesRecycleViewAdapter);
        mSaleRecycleView.setNestedScrollingEnabled(false);
        fb.getAllSalesItem(PRODUCT_CATEGORY_ID, allDiscountPizzaItems, mSalesRecycleViewAdapter, progressBar, mContentLayout);

        // Animation [TEST]
        LinearLayout ll = view.findViewById(R.id.recycler);
        LinearLayout ll2 = view.findViewById(R.id.recycler2);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_up);
        ll.startAnimation(animation);
        ll2.startAnimation(animation);





    }


}
