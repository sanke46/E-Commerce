package com.sanke46.android.e_commerce.ui.orderable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.adapter.RecyclerViewAdapter;
import com.sanke46.android.e_commerce.database.DataBaseHandler;
import com.sanke46.android.e_commerce.model.Item;

import java.util.ArrayList;

/**
 * Created by ilafedoseev on 05.02.17.
 */
public class Drinks extends Fragment {

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview_3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_3);
//        String[] dummyStrings = getResources().getStringArray(R.array.my_items);
//
        DataBaseHandler db = new DataBaseHandler(getActivity());
        ArrayList<Item> drinkList = (ArrayList<Item>) db.getAllItem("Dr");

//        ArrayList<Item> drinkList = new ArrayList<Item>();
//        drinkList.add(new Item(R.drawable.drinks,"Drink1","Comment1",4,"Button1","Button2"));
//        drinkList.add(new Item(R.drawable.drinks2,"Drink2","Comment2",5,"Button1-2","Button2-2"));
//        drinkList.add(new Item(R.drawable.drinks3,"Drink3","Comment3",6,"Button1-3","Button2-3"));

//        ListAdapter listAdapter = new ListAdapter(getActivity().getApplicationContext(),drinkList);
//        listView.setAdapter(listAdapter);

        mLayoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(drinkList);
        mRecyclerView.setAdapter(recyclerViewAdapter);

//                val adapter = FriendsRecyclerViewAdapter(implemets())
//        recyclerView.adapter = adapter
//
//        mLayoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager
//        recyclerView.layoutManager = mLayoutManager
//
//        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
//                fab.setOnClickListener { startActivity(Intent(activity, ChatWithFriendsActivity::class.java)) }
//
//        recyclerView.addItemDecoration(DividerItemDecoration(activity,
//                DividerItemDecoration.VERTICAL))
//
//        return view



    }

}
