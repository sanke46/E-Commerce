package com.sanke46.android.e_commerce.ui.navigation;

import com.sanke46.android.e_commerce.model.Item;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class OrderActivityTest extends TestCase {

    private OrderActivity orderActivity = new OrderActivity();

    public void testGetTotalPrice() {
        int price = 180;
        List<Item> list = new ArrayList<>();
        Item item = new Item();
        item.setPrice(price);
        list.add(item);
        list.add(item);

        assertEquals(String.valueOf(price * 2), orderActivity.getTotalPrice(list));
    }
}