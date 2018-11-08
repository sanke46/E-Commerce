package com.sanke46.android.e_commerce.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.model.Item;

import java.util.ArrayList;
import java.util.List;

public class HistoryListItemsAdapter extends ArrayAdapter<Item> {

    public HistoryListItemsAdapter(@NonNull Context context, ArrayList<Item> itemArrayList) {
        super(context, 0, itemArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Item item = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.history_list_item, parent,false);
        }

        TextView name = convertView.findViewById(R.id.name_history_item);
        TextView number = convertView.findViewById(R.id.number_history_item);
        TextView price = convertView.findViewById(R.id.price_history_item);
        name.setText(item.getName());
        price.setText(String.valueOf(item.getPrice()) + " $");
        return convertView;
    }
}
