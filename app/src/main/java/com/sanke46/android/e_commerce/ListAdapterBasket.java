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

public class ListAdapterBasket extends ArrayAdapter<Item>{

    BasketActivity basketActivity = new BasketActivity();
    private List<Item> itemList = basketActivity.getBasketItem();
    public ListAdapterBasket(Context context, List<Item> arrayList) {
        super(context, 0 ,arrayList);
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Item item = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_basket,parent,false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        Button delete = (Button) convertView.findViewById(R.id.delete);

        image.setImageResource(item.getImageId());
        name.setText(item.getName());
        price.setText(item.getPrice() + " $");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.remove(position);
                basketActivity.setBasketItem(itemList);
                basketActivity.;

            }
        });

        return convertView;
    }

}
