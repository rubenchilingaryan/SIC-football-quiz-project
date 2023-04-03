package com.example.footballquiz.ratings;

public class ModesModel {
    String number;
    String username;
    String rating;
    int imageview;

    public ModesModel(String number, String username, String rating, int imageview) {
        this.number = number;
        this.username = username;
        this.rating = rating;
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

    public int getImageview() {
        return imageview;
    }
}
