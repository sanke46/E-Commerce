package com.sanke46.android.e_commerce.ui.orderable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sanke46.android.e_commerce.database.DataBaseHandler;
import com.sanke46.android.e_commerce.adapter.ListAdapter;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.model.Item;

import java.util.List;

/**
 * Created by ilafedoseev on 05.02.17.
 */
public class Sushi extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview_2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ListView listView = (ListView) view.findViewById(R.id.list_2);
        String[] dummyStrings = getResources().getStringArray(R.array.my_items);

        DataBaseHandler db = new DataBaseHandler(getActivity());
        List<Item> sushiList = db.getAllItem("Su");

//        ArrayList<Item> sushiList = new ArrayList<Item>();
//        sushiList.add(new Item(R.drawable.sushi,"Sushi1","rice, cucumber, fish, soy",10,"Button1","Button2"));
//        sushiList.add(new Item(R.drawable.sushi2,"Sushi2","rice, cucumber, fish, soy",11,"Button1-2","Button2-2"));
//        sushiList.add(new Item(R.drawable.sushi3,"Sushi3","rice, cucumber, fish, soy",12,"Button1-3","Button2-3"));

        ListAdapter listAdapter = new ListAdapter(getActivity().getApplicationContext(),sushiList);
        listView.setAdapter(listAdapter);

    }

}
