package com.sanke46.android.e_commerce.model;

import java.util.HashMap;

public class OrderSecond {
    private String payment;
    private String time;
    private String adress;
    private String phone;
    private HashMap<Integer, Item> listOfBuyProducts;
    private String totalPrice;

    public OrderSecond(String payment, String time, String adress, String phone, HashMap<Integer, Item> listOfBuyProducts, String totalPrice) {
        this.payment = payment;
        this.time = time;
        this.adress = adress;
        this.phone = phone;
        this.listOfBuyProducts = listOfBuyProducts;
        this.totalPrice = totalPrice;
    }

    public OrderSecond() {}

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public HashMap<Integer, Item> getListOfBuyProducts() {
        return listOfBuyProducts;
    }

    public void setListOfBuyProducts(HashMap<Integer, Item> listOfBuyProducts) {
        this.listOfBuyProducts = listOfBuyProducts;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
