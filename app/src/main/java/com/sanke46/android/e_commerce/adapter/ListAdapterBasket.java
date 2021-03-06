package com.sanke46.android.e_commerce.adapter;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.podcopic.animationlib.library.AnimationType;
import com.podcopic.animationlib.library.StartSmartAnimation;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.ViewModel.BasketActivityViewModel;
import com.sanke46.android.e_commerce.model.Item;
import com.sanke46.android.e_commerce.model.ItemBasket;
import com.sanke46.android.e_commerce.ui.navigation.BasketActivity;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ListAdapterBasket extends ArrayAdapter<ItemBasket>{

    private BasketActivityViewModel basketViewModel;
    private BasketActivity basketActivity;
    private List<Item> itemList;
    private static HashMap<Item, Integer> mapBasketItem;

    public ListAdapterBasket(BasketActivity context, List<ItemBasket> arrayList) {
        super(context, 0 ,arrayList);
        this.basketActivity = context;
        this.basketViewModel = new BasketActivityViewModel();
        this.itemList = basketViewModel.getBasketItem();
        this.mapBasketItem = basketViewModel.getMapBasketItem();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ItemBasket itembasket = getItem(position);
        Item item = itembasket.getItem();
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_basket,parent,false);
        }

        final LinearLayout mainBlock = convertView.findViewById(R.id.basketBlock);
        final ImageView image =  convertView.findViewById(R.id.image);
        TextView name = convertView.findViewById(R.id.name);
        ImageView plus = convertView.findViewById(R.id.plusBasket);
        ImageView minus = convertView.findViewById(R.id.minusBasket);
        TextView countProduct = convertView.findViewById(R.id.countProduct);
        TextView gramms = convertView.findViewById(R.id.grammBasket);
        TextView price =  convertView.findViewById(R.id.price);
        ImageView delete = convertView.findViewById(R.id.delete);

        Picasso.with(getContext()).load(item.getImageUrl()).into(image);
        name.setText(item.getName());
        if(item.isSales()) {
            price.setText(item.getDiscontPrice() + " $");
        } else {
            price.setText(item.getPrice() + " $");
        }
        countProduct.setText(itembasket.getCountNumber() + "");
        gramms.setText(item.getGramms() + " g");

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itembasket.getCountNumber() != 1){
                    itemList.remove(item);
                    basketActivity.refreshUi();
                    notifyDataSetChanged();
                    notifyDataSetInvalidated();
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemList.add(item);
                basketActivity.refreshUi();
                notifyDataSetChanged();
                notifyDataSetInvalidated();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartSmartAnimation.startAnimation(mainBlock, AnimationType.SlideOutRight, 1000,0, true);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                            for (int i = 0; i < itembasket.getCountNumber() ; i++) {
                                itemList.remove(item);
                            }

                        basketActivity.refreshUi();
                        notifyDataSetChanged();
                        notifyDataSetInvalidated();
                    }
                }, 1000);
            }
        });

        return convertView;
    }
}