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

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.adapter.RecyclerViewAdapter;
import com.sanke46.android.e_commerce.adapter.SalesRecyclerViewAdapter;
import com.sanke46.android.e_commerce.database.DataBaseHandler;
import com.sanke46.android.e_commerce.fireBase.FirebaseHandler;
import com.sanke46.android.e_commerce.model.Item;

import java.util.ArrayList;

/**
 * Created by ilafedoseev on 05.02.17.
 */
public class Pizza extends Fragment {

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mSaleLayoutManager;
    private RecyclerView mSalerecycleView;
    private SalesRecyclerViewAdapter salesImageAdapter;

    private StorageReference mStorageRef;
    private final ArrayList<Item> salesItem = new ArrayList<>();
    private final ArrayList<Item> allItem = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview_1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        FirebaseHandler fb = new FirebaseHandler();
        DataBaseHandler db = new DataBaseHandler(getActivity());

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mRecyclerView = view.findViewById(R.id.list_1);
        mRecyclerView.setNestedScrollingEnabled(false);

        mLayoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(allItem, getContext());
        mRecyclerView.setAdapter(recyclerViewAdapter);
        fb.getAllItem("pizza", allItem,recyclerViewAdapter);

        // Sales block with adapter
        mSalerecycleView = view.findViewById(R.id.list_sale);
        mSaleLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mSalerecycleView.setLayoutManager(mSaleLayoutManager);
        salesImageAdapter = new SalesRecyclerViewAdapter(getContext(),salesItem, R.layout.item_sale);
        mSalerecycleView.setAdapter(salesImageAdapter);
        mSalerecycleView.setNestedScrollingEnabled(false);
        fb.getAllSalesItem("pizza", salesItem,salesImageAdapter);

        // Animation
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.recycler);
        LinearLayout ll2 = (LinearLayout) view.findViewById(R.id.recycler2);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_up);
        ll.startAnimation(animation);
        ll2.startAnimation(animation);

    }
}
