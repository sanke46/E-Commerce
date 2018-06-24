package com.sanke46.android.e_commerce.ui.navigation;

import android.graphics.Paint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.podcopic.animationlib.library.AnimationType;
import com.podcopic.animationlib.library.StartSmartAnimation;
import com.sanke46.android.e_commerce.model.InfoDetail;
import com.sanke46.android.e_commerce.model.Item;

import java.util.List;

public class DetailActivityView {

    private BasketActivity basketActivity = new BasketActivity();
    private List<Item> itemList = basketActivity.getBasketItem();
    private Item item;

    public DetailActivityView(Item item) {
        this.item = item;
    }

    public void displayNormalOrSalesPrice(LinearLayout linearPrice, TextView linerWithNotDiscont, TextView priceTextView, TextView salesPriceTextView) {

        if (item.isSales()) {
            // VISIBLE sales block + GONE main price
            setVisibility(linearPrice, View.VISIBLE);
            setVisibility(linerWithNotDiscont, View.GONE);

            priceTextView.setPaintFlags(priceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            setText(String.valueOf(item.getPrice()) + " $", priceTextView);
            setText(String.valueOf(item.getDiscontPrice()) + " $", salesPriceTextView);
        } else {
            // GONE sales block + VISIBLE main price
            setVisibility(linearPrice, View.VISIBLE);
            setVisibility(linerWithNotDiscont, View.GONE);

            setText(String.valueOf(item.getPrice()) + "$", priceTextView);
        }
    }

    public void addProductInfoToList(List<InfoDetail> arrayInfoProduct) {
        arrayInfoProduct.add(new InfoDetail("Gramms", String.valueOf(item.getGramms())));
        arrayInfoProduct.add(new InfoDetail("Kalories", String.valueOf(item.getKalories())));
        arrayInfoProduct.add(new InfoDetail("Protein", String.valueOf(item.getProtein())));
        arrayInfoProduct.add(new InfoDetail("Carb", String.valueOf(item.getCarbohydrates())));
        arrayInfoProduct.add(new InfoDetail("Status", item.getStatus()));
        arrayInfoProduct.add(new InfoDetail("ComeFrom", item.getComeFrom()));
    }

    public void addProductToBasket(View button, TextView countOfProduct) {
        StartSmartAnimation.startAnimation(button, AnimationType.FadeOut, 600, 0, false);
        StartSmartAnimation.startAnimation(button, AnimationType.FadeIn, 600, 900, false);

        if(viewToInt(countOfProduct) == 1) {
            itemList.add(item);
            basketActivity.setBasketItem(itemList);
        } else {
            for (int i = 0; i < viewToInt(countOfProduct); i++) {
                itemList.add(item);
                basketActivity.setBasketItem(itemList);
            }
        }
    }

    public void setText(String text, TextView textView) {
        textView.setText(text);
    }

    public void setVisibility(View view, Boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    public void setVisibility(View view, int isVisible) {
        view.setVisibility(isVisible);
    }

    private Integer viewToInt(TextView textView) {
        return Integer.parseInt(String.valueOf(textView.getText()));
    }
}
