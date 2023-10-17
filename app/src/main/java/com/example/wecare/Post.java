package com.example.wecare;

import android.widget.ImageView;

public class Post {


    public Post() {

    }

    public String  getImageUrl() {
        return imageUrl;
    }



    public String getTimeOfPost() {
        return timeOfPost;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTimeOfPost(String timeOfPost) {
        this.timeOfPost = timeOfPost;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return description;
    }

    private String imageUrl;
    private String timeOfPost;
    private String title;
    private String description;

    public Post(String imageUrl, String timeOfPost, String title, String description) {
        this.imageUrl = imageUrl;

        this.timeOfPost = timeOfPost;
        this.title = title;
        this.description = description;
    }

    public void setImageView(ImageView imageView) {
    }
}




