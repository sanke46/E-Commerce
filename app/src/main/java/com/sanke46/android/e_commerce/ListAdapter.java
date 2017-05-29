package com.sanke46.android.e_commerce;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ilafedoseev on 07.02.17.
 */

public class ListAdapter extends ArrayAdapter<Item>{

    BasketActivity basketActivity = new BasketActivity();
    public List<Integer> itemList = basketActivity.getBasketItem();
    public ListAdapter(Context context, List<Item> arrayList) {
        super(context, 0 ,arrayList);
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Item item = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list,parent,false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView comment = (TextView) convertView.findViewById(R.id.comment);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        Button buttonOne = (Button) convertView.findViewById(R.id.buttonOne);
        Button buttonTwo = (Button) convertView.findViewById(R.id.buttonTwo);

        image.setImageResource(item.getImageId());
        name.setText(item.getName());
        comment.setText(item.getComment());
        price.setText(item.getPrice() + " $");
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("add");
                System.out.println(getItemId(position));
                itemList.add((int) getItemId(position));
                basketActivity.setBasketItem(itemList);
            }
        });

        return convertView;
    }

}
