package com.example.ecommerceapp.Model;

import android.media.Rating;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductModel {
    public int id;
    public String title;
    public double price;
    public String description;
    public String category;
    public String image;
    public Rating rating;

    public ProductModel(int id, String title, double price, String description, String category, String image, Rating rating) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public Rating getRating() {
        return rating;
    }
}
