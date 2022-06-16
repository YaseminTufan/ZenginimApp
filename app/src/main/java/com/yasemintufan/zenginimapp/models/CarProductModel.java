package com.yasemintufan.zenginimapp.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class CarProductModel {

    private String name;
    private double price;
    private String img_url;

    public CarProductModel(String name, double price, String img_url) {
        this.name = name;
        this.price = price;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", img_url='" + img_url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarProductModel that = (CarProductModel) o;
        return Double.compare(that.getPrice(), getPrice()) == 0 && getName().equals(that.getName()) && getImg_url().equals(that.getImg_url());
    }


    public static DiffUtil.ItemCallback<CarProductModel> itemCallback = new DiffUtil.ItemCallback<CarProductModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull CarProductModel oldItem, @NonNull CarProductModel newItem) {
            return oldItem.getImg_url().equals(newItem.getImg_url());
        }

        @Override
        public boolean areContentsTheSame(@NonNull CarProductModel oldItem, @NonNull CarProductModel newItem) {
            return oldItem.equals(newItem);
        }
    };
    @BindingAdapter("android:carImage")
    public static void loadImage (ImageView imageView,String imageUrl) {
        Glide.with(imageView)
                .load(imageUrl)
                .fitCenter()
                .into(imageView);
    }
}
