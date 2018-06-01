package com.sanke46.android.e_commerce.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.model.Item;
import com.sanke46.android.e_commerce.ui.navigation.BasketActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapterBasket extends ArrayAdapter<Item>{

    BasketActivity basketActivity;
    private List<Item> itemList;

    public ListAdapterBasket(BasketActivity context, List<Item> arrayList) {
        super(context, 0 ,arrayList);
        this.basketActivity = context;
        this.itemList = basketActivity.getBasketItem();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Item item = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_basket,parent,false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView gramms = (TextView) convertView.findViewById(R.id.grammBasket);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        ImageView delete = (ImageView) convertView.findViewById(R.id.delete);

//        image.setImageResource(item.getImageId());
        Picasso.with(getContext()).load(item.getImageUrl()).into(image);
        name.setText(item.getName());
        if(item.isSales()) {
            price.setText(item.getDiscontPrice() + " $");
        } else {
            price.setText(item.getPrice() + " $");
        }
        gramms.setText(item.getGramms() + " g");

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.remove(position);
                basketActivity.refreshUi();

                notifyDataSetChanged();
                notifyDataSetInvalidated();
            }
        });

        return convertView;
    }
}
