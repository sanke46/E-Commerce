package com.sanke46.android.e_commerce;

import java.util.List;

/**
 * Created by ilafedoseev on 22.03.17.
 */

public interface DBHandlerInterface {
    public void addItem();
    public Item getItem();
    public List<Item> getAllItem();
    public int getItemCounter();
    public int updateItem();
    public void deleteItem();
    public void deleteAll();
}
