package com.sanke46.android.e_commerce.model;

public class UserOrder {

    private String name;
    private String phone;
    private String city;
    private String street;
    private String house;
    private String flat;

    public UserOrder() {

    }

    public UserOrder(String name, String phone, String city, String street, String house, String flat) {
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }
}
