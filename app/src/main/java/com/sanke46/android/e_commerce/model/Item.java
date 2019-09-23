package com.sanke46.android.e_commerce.model;

/**
 * Created by ilafedoseev on 07.02.17.
 */

public class Item {

    private int id;
    private int imageId;
    private String imageName;
    private String imageUrl;
    private String name;
    private String comment;
    private int price;
    private int discontPrice;
    private int gramms;
    private int kalories;
    private int protein;
    private int carbohydrates; // Углеводы
    private boolean vegetarian;
    private boolean spice;
    private boolean sales;
    private String status;
    private String comeFrom;
    private String buttonOne;
    private String buttonTwo;

    public Item(int id, int imageId, String name, String comment, int price, int discontPrice, int gramms, int kalories, int protein, int carbohydrates, boolean vegetarian, boolean spice, boolean sales, String status, String comeFrom, String buttonOne, String buttonTwo) {
        this.id = id;
        this.imageId = imageId;
        this.name = name;
        this.comment = comment;
        this.price = price;
        this.discontPrice = discontPrice;
        this.gramms = gramms;
        this.kalories = kalories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.vegetarian = vegetarian;
        this.spice = spice;
        this.sales = sales;
        this.status = status;
        this.comeFrom = comeFrom;
        this.buttonOne = buttonOne;
        this.buttonTwo = buttonTwo;
    }

    public Item(int id, String imageName, String imageUrl, String name, String comment, int price, int discontPrice, int gramms, int kalories, int protein, int carbohydrates, boolean vegetarian, boolean spice, boolean sales, String status, String comeFrom, String buttonOne, String buttonTwo) {
        this.id = id;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.name = name;
        this.comment = comment;
        this.price = price;
        this.discontPrice = discontPrice;
        this.gramms = gramms;
        this.kalories = kalories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.vegetarian = vegetarian;
        this.spice = spice;
        this.sales = sales;
        this.status = status;
        this.comeFrom = comeFrom;
        this.buttonOne = buttonOne;
        this.buttonTwo = buttonTwo;
    }

    public Item(int id, int imageId, String name, String comment, int price, String buttonOne, String buttonTwo){
        this.id = id;
        this.imageId = imageId;
        this.name = name;
        this.comment = comment;
        this.price = price;
        this.buttonOne = buttonOne;
        this.buttonTwo = buttonTwo;
    }

    public Item(String name, String comment, int price, int discontPrice, int gramms, int kalories) {
        this.name = name;
        this.comment = comment;
        this.price = price;
        this.discontPrice = discontPrice;
        this.gramms = gramms;
        this.kalories = kalories;
    }

    public Item(int imageId, String name, String comment, int price, String buttonOne, String buttonTwo){
        this.imageId = imageId;
        this.name = name;
        this.comment = comment;
        this.price = price;
        this.buttonOne = buttonOne;
        this.buttonTwo = buttonTwo;
    }

    public Item(int id) {
        this.id = id;
    }

    public Item(){}

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

    public int getDiscontPrice() {
        return discontPrice;
    }

    public int getGramms() {
        return gramms;
    }

    public int getKalories() {
        return kalories;
    }

    public int getProtein() {
        return protein;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public boolean isSpice() {
        return spice;
    }

    public boolean isSales() {
        return sales;
    }

    public String getStatus() {
        return status;
    }

    public String getComeFrom() {
        return comeFrom;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageUrl() {
        return imageUrl;
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

    public void setDiscontPrice(int discontPrice) {
        this.discontPrice = discontPrice;
    }

    public void setGramms(int gramms) {
        this.gramms = gramms;
    }

    public void setKalories(int kalories) {
        this.kalories = kalories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public void setSpice(boolean spice) {
        this.spice = spice;
    }

    public void setSales(boolean sales) {
        this.sales = sales;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Item{" +
                "price=" + price +
                '}';
    }
}
