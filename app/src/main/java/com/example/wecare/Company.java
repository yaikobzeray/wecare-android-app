package com.example.wecare;

public class Company {
    String name;
    String subscribers;
    boolean isVerified;
    boolean isSubscribed;

    public Company(String name, String subscribers, boolean isVerified, boolean isSubscribed) {
        this.name = name;
        this.subscribers = subscribers;
        this.isVerified = isVerified;
        this.isSubscribed = isSubscribed;
    }

    public String getName() {
        return name;
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
