package com.example.footballquiz.ratings.recyclerView;

public class ModesModel {
    String number;
    String username;
    String rating;
    String imageUrl;
    int imageview;

    public ModesModel(String number, String username, String rating, String imageUrl, int imageview) {
        this.number = number;
        this.username = username;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.imageview = imageview;
    }

    public String getNumber() {
        return number;
    }

    public String getUsername() {
        return username;
    }

    public String getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getImageview() {
        return imageview;
    }
}
