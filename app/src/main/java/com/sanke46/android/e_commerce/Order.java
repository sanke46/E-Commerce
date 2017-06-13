package com.sanke46.android.e_commerce;

public class Order {
    private int id;
    private String etnS,etcS,etsS,ethnS,etfS,etpnS;

    public Order(int id,String etnS,String etcS,String etsS,String ethnS, String etfS, String etpnS){
        this.id = id;
        this.etnS = etnS;
        this.etcS = etcS;
        this.etsS = etsS;
        this.ethnS = ethnS;
        this.etfS = etfS;
        this.etpnS = etpnS;
    }

    public Order() {

    }

    //getters
    public String getEtcS() {
        return etcS;
    }

    public String getEtfS() {
        return etfS;
    }

    public String getEthnS() {
        return ethnS;
    }

    public String getEtnS() {
        return etnS;
    }

    public String getEtpnS() {
        return etpnS;
    }

    public String getEtsS() {
        return etsS;
    }

    public int getId() {
        return id;
    }


    //setters

    public void setEtcS(String etcS) {
        this.etcS = etcS;
    }

    public void setEtfS(String etfS) {
        this.etfS = etfS;
    }

    public void setEthnS(String ethnS) {
        this.ethnS = ethnS;
    }

    public void setEtnS(String etnS) {
        this.etnS = etnS;
    }

    public void setEtpnS(String etpnS) {
        this.etpnS = etpnS;
    }

    public void setEtsS(String etsS) {
        this.etsS = etsS;
    }

    public void setId(int id) {
        this.id = id;
    }
}
