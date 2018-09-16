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
import com.sanke46.android.e_commerce.model.InfoDetail;

import java.util.List;

public class ListInformationAdapter extends ArrayAdapter<InfoDetail>{

    public ListInformationAdapter(@NonNull Context context, List<InfoDetail> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final InfoDetail item = getItem(position);

        if(convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_information, parent, false);
        }

        TextView text = convertView.findViewById(R.id.text);
        TextView value = convertView.findViewById(R.id.value);

        text.setText(item.getName());
        value.setText(item.getValue());

        return convertView;
    }
}
