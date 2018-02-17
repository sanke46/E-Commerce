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
    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mSaleLayoutManager;
    private RecyclerView mSalerecycleView;
    private SalesRecyclerViewAdapter salesImageAdapter;

    private RecyclerView.LayoutManager mSaleLayoutManager2;
    private RecyclerView mSalerecycleView2;
    private SalesRecyclerViewAdapter salesImageAdapter2;

    private StorageReference mStorageRef;
    private final ArrayList<Item> salesItem = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview_1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mRecyclerView = view.findViewById(R.id.list_1);
        mRecyclerView.setNestedScrollingEnabled(false);

        DataBaseHandler db = new DataBaseHandler(getActivity());
        ArrayList<Item> pizzaList = (ArrayList<Item>) db.getAllItem("Pi");

        mLayoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(pizzaList);
        mRecyclerView.setAdapter(recyclerViewAdapter);

        FirebaseHandler fb = new FirebaseHandler();

        mSalerecycleView = view.findViewById(R.id.list_sale);
        mSaleLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mSalerecycleView.setLayoutManager(mSaleLayoutManager);
        salesImageAdapter = new SalesRecyclerViewAdapter(getContext(),salesItem, R.layout.item_sale);
        mSalerecycleView.setAdapter(salesImageAdapter);
        mSalerecycleView.setNestedScrollingEnabled(false);
        fb.getAllSalesItem(salesItem,salesImageAdapter);



//        ArrayList<ImageSales> imageSalesArray2 = new ArrayList<>();
//        imageSalesArray2.add(new ImageSales(R.drawable.image));
//        imageSalesArray2.add(new ImageSales(R.drawable.image2));
//        imageSalesArray2.add(new ImageSales(R.drawable.image3));
//
//        mSalerecycleView2 = view.findViewById(R.id.list_info);
//        mSaleLayoutManager2 = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
//        mSalerecycleView2.setLayoutManager(mSaleLayoutManager2);
//        salesImageAdapter2 = new SalesRecyclerViewAdapter(imageSalesArray2, R.layout.list4);
//        mSalerecycleView2.setAdapter(salesImageAdapter2);
//        mSalerecycleView2.setNestedScrollingEnabled(false);


    }
}
