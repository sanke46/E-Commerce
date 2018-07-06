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
    public static HashMap<Integer, Item> mapBasketItem = new HashMap();
    public int sum;

    public List<Item> getBasketItem() {
        return basketItem;
    }

    public void setBasketItem(List<Item> basketItem) {
        this.basketItem = basketItem;
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

    public void sortBasketList() {
        mapBasketItem = new HashMap<>();
        for (Item item : basketItem) {
            int count = 0;
            for (Item item2 : basketItem) {
                if(item.equals(item2)) count++;
            }
            mapBasketItem.put(count, item);
            System.out.print(item);
        }

        System.out.println(basketItem.size());
        System.out.println(mapBasketItem);
    }

    public void addToList() {
        basketItemBasket = new ArrayList<>();
        for (Map.Entry<Integer, Item> entry : mapBasketItem.entrySet()) {
            basketItemBasket.add(new ItemBasket(entry.getKey(), entry.getValue()));
            System.out.println(basketItemBasket.size() + "basket");
        }
    }
}
