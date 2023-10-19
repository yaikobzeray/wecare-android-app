package com.example.wecare;

public class Company {
    String name;
    String imgUrl;
    String subscribers;
    boolean isVerified;
    boolean isSubscribed;

    public Company() {

    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSubscribers() {
        return subscribers;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }
}
