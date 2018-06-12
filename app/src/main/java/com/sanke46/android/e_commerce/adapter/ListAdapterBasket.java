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
import com.sanke46.android.e_commerce.model.Item;
import com.sanke46.android.e_commerce.model.ItemBasket;
import com.sanke46.android.e_commerce.ui.navigation.BasketActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapterBasket extends ArrayAdapter<ItemBasket>{

    private BasketActivity basketActivity;
    private List<Item> itemList;

    public ListAdapterBasket(BasketActivity context, List<ItemBasket> arrayList) {
        super(context, 0 ,arrayList);
        this.basketActivity = context;
        this.itemList = basketActivity.getBasketItem();
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
        final ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView countProduct = (TextView) convertView.findViewById(R.id.countProduct);
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
        countProduct.setText(itembasket.getCountNumber() + "");
        gramms.setText(item.getGramms() + " g");

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                TranslateAnimation animate = new TranslateAnimation(0,0,0,-image.getWidth());
//                animate.setDuration(1000);
//                mainBlock.startAnimation(animate);
//                mainBlock.setVisibility(View.INVISIBLE);

                StartSmartAnimation.startAnimation(mainBlock, AnimationType.SlideOutRight, 1000,0, true);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        itemList.remove(position);
                        basketActivity.refreshUi();
//
                        notifyDataSetChanged();
                        notifyDataSetInvalidated();
                    }
                }, 1000);
            }
        });

        return convertView;
    }
}