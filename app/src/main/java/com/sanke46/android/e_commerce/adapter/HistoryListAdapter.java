package com.sanke46.android.e_commerce.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.model.Chat;
import com.sanke46.android.e_commerce.model.Order;

import java.util.ArrayList;

public class HistoryListAdapter extends ArrayAdapter<Order> {

    public HistoryListAdapter(@NonNull Context context, ArrayList<Order> listHistory) {
        super(context, 0, listHistory);
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Order order = getItem(position);

        if (convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.history_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name_history);
        TextView date = convertView.findViewById(R.id.date_history);
        TextView price = convertView.findViewById(R.id.price_history);

        name.setText("#" + (position + 1));
        date.setText(order.getTime());
        price.setText(order.getTotalPrice() + " $");

        return convertView;
    }
}
