package com.sanke46.android.e_commerce.model;

public class ItemBasket {
    private Integer countNumber;
    private Item item;

    public ItemBasket(Integer countNumber, Item item) {
        this.countNumber = countNumber;
        this.item = item;
    }

    public Integer getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(Integer countNumber) {
        this.countNumber = countNumber;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
