package com.sanke46.android.e_commerce.model;

import java.io.Serializable;

public class Chat implements Serializable {

    private String text;
    private String user;
    private String time;

    public Chat(String text, String user, String time) {
        this.text = text;
        this.user = user;
        this.time = time;
    }

    public Chat() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
