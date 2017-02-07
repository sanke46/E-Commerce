package com.sanke46.android.e_commerce;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilafedoseev on 07.02.17.
 */

public class ListAdapter extends ArrayAdapter<Item>{


    public ListAdapter(Context context, List<Item> arrayList) {
        super(context, 0 ,arrayList);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list,parent,false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView comment = (TextView) convertView.findViewById(R.id.comment);

        name.setText(item.getName());
        comment.setText(item.getComment());

        return convertView;
    }
}
