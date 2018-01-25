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
public class Sushi extends Fragment {

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview_2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mRecyclerView = view.findViewById(R.id.list_2);
//        String[] dummyStrings = getResources().getStringArray(R.array.my_items);

        DataBaseHandler db = new DataBaseHandler(getActivity());
        ArrayList<Item> sushiList = (ArrayList<Item>) db.getAllItem("Su");

//        ArrayList<Item> sushiList = new ArrayList<Item>();
//        sushiList.add(new Item(R.drawable.sushi,"Sushi1","rice, cucumber, fish, soy",10,"Button1","Button2"));
//        sushiList.add(new Item(R.drawable.sushi2,"Sushi2","rice, cucumber, fish, soy",11,"Button1-2","Button2-2"));
//        sushiList.add(new Item(R.drawable.sushi3,"Sushi3","rice, cucumber, fish, soy",12,"Button1-3","Button2-3"));

//        ListAdapter listAdapter = new ListAdapter(getActivity().getApplicationContext(),sushiList);
//        listView.setAdapter(listAdapter);
        mLayoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(sushiList);
        mRecyclerView.setAdapter(recyclerViewAdapter);

    }

}
