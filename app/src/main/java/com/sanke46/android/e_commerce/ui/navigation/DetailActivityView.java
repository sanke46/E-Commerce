package com.sanke46.android.e_commerce.ui.navigation;

import android.widget.TextView;

import com.sanke46.android.e_commerce.model.InfoDetail;
import com.sanke46.android.e_commerce.model.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailActivityView implements Serializable{

    private BasketActivity basketActivity = new BasketActivity();
    private List<Item> itemList = basketActivity.getBasketItem();
    private Integer countProductToBasket;
    public Item item;


    public DetailActivityView(Item item) {
        this.item = item;
        this.countProductToBasket = 0;
    }


    public List getListOfProductInfo() {
        List<InfoDetail> arrayInfoProduct = new ArrayList<>();
        arrayInfoProduct.add(new InfoDetail("Gramms", String.valueOf(item.getGramms())));
        arrayInfoProduct.add(new InfoDetail("Kalories", String.valueOf(item.getKalories())));
        arrayInfoProduct.add(new InfoDetail("Protein", String.valueOf(item.getProtein())));
        arrayInfoProduct.add(new InfoDetail("Carb", String.valueOf(item.getCarbohydrates())));
        arrayInfoProduct.add(new InfoDetail("Status", item.getStatus()));
        arrayInfoProduct.add(new InfoDetail("ComeFrom", item.getComeFrom()));

        return arrayInfoProduct;
    }

    public void addProductToBasket(TextView countOfProduct) {
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

    private Integer viewToInt(TextView textView) {
        return Integer.parseInt(String.valueOf(textView.getText()));
    }

    public String plusCountProductToBasket() {
        countProductToBasket++;
        return String.valueOf(countProductToBasket);
    }

    public String minusCountProductToBasket() {
        countProductToBasket--;
        return String.valueOf(countProductToBasket);
    }

    public void resetCountProductsToBasket() {
        countProductToBasket = 0;
    }
}
