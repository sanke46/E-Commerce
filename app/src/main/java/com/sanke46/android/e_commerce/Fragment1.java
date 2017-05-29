package com.sanke46.android.e_commerce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * Created by ilafedoseev on 05.02.17.
 */
public class Fragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview_1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ListView listView = (ListView) view.findViewById(R.id.list);
        String[] dummyStrings = getResources().getStringArray(R.array.my_items);

        DataBaseHandler db = new DataBaseHandler(getActivity());
        List<Item> pizzaList = db.getAllItem("Pi");

        //        ArrayList<Item> pizzaList = new ArrayList<Item>();
//        pizzaList.add(new Item(R.drawable.pizza,"Pizza1","tomatoes, onions, olives, cheese, chicken",19,"Button1","Button2"));
//        pizzaList.add(new Item(R.drawable.pizza2,"Pizza2","tomatoes, onions, olives, cheese, chicken",20,"Button1-2","Button2-2"));
//        pizzaList.add(new Item(R.drawable.pizza3,"Pizza3","tomatoes, onions, olives, cheese, chicken",21,"Button1-3","Button2-3"));

        ListAdapter listAdapter = new ListAdapter(getActivity().getApplicationContext(),pizzaList);
        listView.setAdapter(listAdapter);

    }
}
