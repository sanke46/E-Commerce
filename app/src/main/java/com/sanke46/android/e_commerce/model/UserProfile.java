package com.sanke46.android.e_commerce.model;

public class UserProfile {

    private String name;
    private String email;
    private String phone;
    private String password;
    private String city;
    private String street;
    private String house;
    private String flat;

    public UserProfile() {}

    /** For Basket Activity **/
    public UserProfile(String name, String phone, String city, String street, String house, String flat) {
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    /** For Profile Activity **/
    public UserProfile(String name, String email, String phone, String password, String city, String street, String house, String flat) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
