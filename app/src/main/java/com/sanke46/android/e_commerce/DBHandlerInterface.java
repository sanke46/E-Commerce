package com.sanke46.android.e_commerce;

import java.util.List;

/**
 * Created by ilafedoseev on 22.03.17.
 */

public interface DBHandlerInterface {
    public void addItem(Item item);
    public Item getItem(int id);
    public List<Item> getAllItem();
    public int getItemCounter();
    public int updateItem(Item item);
    public void deleteItem(Item item);
    public void deleteAll();
}
