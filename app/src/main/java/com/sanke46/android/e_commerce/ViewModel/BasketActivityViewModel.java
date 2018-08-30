package com.sanke46.android.e_commerce.ViewModel;

import android.widget.Button;

import com.sanke46.android.e_commerce.model.Item;
import com.sanke46.android.e_commerce.model.ItemBasket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketActivityViewModel {

    public static List<Item> basketItem = new ArrayList<Item>();
    public static List<ItemBasket> basketItemBasket = new ArrayList<ItemBasket>();
    public static HashMap<Item, Integer> mapBasketItem = new HashMap();
    public int sum;

    public List<Item> getBasketItem() {
        return basketItem;
    }

    public void setBasketItem(List<Item> basketItem) {
        this.basketItem = basketItem;
    }

    public HashMap<Item, Integer> getMapBasketItem() {
        return mapBasketItem;
    }

    public void setMapBasketItem(HashMap<Item, Integer> mapBasketItem) {
        BasketActivityViewModel.mapBasketItem = mapBasketItem;
    }

    public void refreshTotalPrice(Button button) {
        sum = 0;
        for (int i = 0; i < basketItem.size(); i++) {
            if (basketItem.get(i).isSales()) {
                sum += basketItem.get(i).getDiscontPrice();
            } else {
                sum += basketItem.get(i).getPrice();
            }
        }
        button.setText("ORDER - " + sum + " $");
    }

    public void sortListToMap() {
        mapBasketItem = new HashMap<>();
        for (Item item : basketItem) {
            int count = 0;
            for (Item item2 : basketItem) {
                if(item.equals(item2)) count++;
            }
            mapBasketItem.put(item, count);
        }

        System.out.println(mapBasketItem + ": mapBasketItem");
    }

    public void sortMapToList() {
        basketItemBasket = new ArrayList<>();
        for (Map.Entry<Item, Integer> entry : mapBasketItem.entrySet()) {
            basketItemBasket.add(new ItemBasket(entry.getValue(), entry.getKey()));
            System.out.println(basketItemBasket.size() + ": basket items");
        }
    }

    public void cleanBasket() {
        basketItem.clear();
    }
}
