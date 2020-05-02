package com.example.sunhan.pethome1.beans;

import android.graphics.Bitmap;

public class ServiceFujinInfo {
    private String shopname;
    private int score;
    private int distance;
    private int price;
    private String place;
    private int salesnumber;

    private String imagePath;
    private Bitmap bitmap;

    public ServiceFujinInfo(String shopname, int score, int distance, int price, String place, int salesnumber, String imagePath, Bitmap bitmap) {
        this.shopname = shopname;
        this.score = score;
        this.distance = distance;
        this.price = price;
        this.place = place;
        this.salesnumber = salesnumber;
        this.imagePath = imagePath;
        this.bitmap = bitmap;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getSalesnumber() {
        return salesnumber;
    }

    public void setSalesnumber(int salesnumber) {
        this.salesnumber = salesnumber;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
