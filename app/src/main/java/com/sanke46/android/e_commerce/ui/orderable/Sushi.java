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

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.adapter.RecyclerViewAdapter;
import com.sanke46.android.e_commerce.adapter.SalesRecyclerViewAdapter;
import com.sanke46.android.e_commerce.fireBase.FirebaseHandler;
import com.sanke46.android.e_commerce.model.Item;

import java.util.ArrayList;

public class Sushi extends Fragment {

    private static final String PRODUCT_CATEGORY_ID = "sushi";
    private static final String TAG = Sushi.class.getSimpleName();

    private final ArrayList<Item> allSushiItem = new ArrayList<>();
    private final ArrayList<Item> allDiscountSushiItem = new ArrayList<>();

    // First RecycleView
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    // Second RecycleView
    private RecyclerView.LayoutManager mSaleLayoutManager;
    private RecyclerView mSaleRecycleView;
    private SalesRecyclerViewAdapter mSalesRecycleViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview_2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        FirebaseHandler fb = new FirebaseHandler();

        // All products [RecycleView + Adapter + LayoutManager + FB]
        mRecyclerView = view.findViewById(R.id.list_2);
        mLayoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(allSushiItem, getContext());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        fb.getAllItem(PRODUCT_CATEGORY_ID, allSushiItem, mRecyclerViewAdapter);

        // Discount [RecycleView + Adapter + LayoutManager + FB]
        mSaleRecycleView = view.findViewById(R.id.list_sale_2);
        mSaleLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mSaleRecycleView.setLayoutManager(mSaleLayoutManager);
        mSalesRecycleViewAdapter = new SalesRecyclerViewAdapter(getContext(), allDiscountSushiItem, R.layout.item_sale);
        mSaleRecycleView.setAdapter(mSalesRecycleViewAdapter);
        mSaleRecycleView.setNestedScrollingEnabled(false);
        fb.getAllSalesItem(PRODUCT_CATEGORY_ID, allDiscountSushiItem, mSalesRecycleViewAdapter);
    }
}
