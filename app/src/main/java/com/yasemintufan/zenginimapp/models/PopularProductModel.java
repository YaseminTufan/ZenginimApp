package com.yasemintufan.zenginimapp.models;

public class PopularProductModel {

    String img_url;
    int price;
    String name;

    public PopularProductModel() {
    }

    public PopularProductModel(String img_url, int price, String name) {
        this.img_url = img_url;
        this.price = price;
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}