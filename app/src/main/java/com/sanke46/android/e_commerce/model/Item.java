package com.sanke46.android.e_commerce.model;

/**
 * Created by ilafedoseev on 07.02.17.
 */

public class Item {

    private int id;
    private int imageId;
    private String name;
    private String comment;
    private int price;
    private String buttonOne;
    private String buttonTwo;

    public Item(int id, int imageId, String name, String comment,int price,String buttonOne,String buttonTwo){
        this.id = id;
        this.imageId = imageId;
        this.name = name;
        this.comment = comment;
        this.price = price;
        this.buttonOne = buttonOne;
        this.buttonTwo = buttonTwo;
    }

    public Item(int imageId, String name, String comment,int price,String buttonOne,String buttonTwo){
        this.imageId = imageId;
        this.name = name;
        this.comment = comment;
        this.price = price;
        this.buttonOne = buttonOne;
        this.buttonTwo = buttonTwo;
    }

    public Item() {

    }

    // GET methods

    public int getId() {
        return id;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public int getPrice() {
        return price;
    }

    public String getButtonOne() {
        return buttonOne;
    }

    public String getButtonTwo() {
        return buttonTwo;
    }

    // SET methods

    public void setId(int id) {
        this.id = id;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setButtonOne(String buttonOne) {
        this.buttonOne = buttonOne;
    }

    public void setButtonTwo(String buttonTwo) {
        this.buttonTwo = buttonTwo;
    }

    @Override
    public String toString() {
        return "Item{" +
                "price=" + price +
                '}';
    }
}
