package com.sanke46.android.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ilafedoseev on 05.02.17.
 */
public class Fragment3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview_3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ListView listView = (ListView) view.findViewById(R.id.list_3);
        String[] dummyStrings = getResources().getStringArray(R.array.my_items);

        ArrayList<Item> drinkList = new ArrayList<Item>();
        drinkList.add(new Item(1,"Title1","Comment1",19,"Button1","Button2"));
        drinkList.add(new Item(2,"Title2","Comment2",20,"Button1-2","Button2-2"));
        drinkList.add(new Item(3,"Title3","Comment3",21,"Button1-3","Button2-3"));

        ListAdapter listAdapter = new ListAdapter(getActivity().getApplicationContext(),drinkList);
        listView.setAdapter(listAdapter);

    }

}
