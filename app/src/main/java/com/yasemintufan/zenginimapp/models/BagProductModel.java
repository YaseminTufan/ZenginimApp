package com.yasemintufan.zenginimapp.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class BagProductModel {

    String img_url;
    String name;
    int price;

    public BagProductModel() {
    }

    public BagProductModel(String img_url, String name, int price) {
        this.img_url = img_url;
        this.name = name;
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BagProductModel{" +
                "img_url='" + img_url + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BagProductModel that = (BagProductModel) o;
        return getPrice() == that.getPrice() && Objects.equals(getImg_url(), that.getImg_url()) && Objects.equals(getName(), that.getName());
    }
    public static DiffUtil.ItemCallback<BagProductModel>itemCallback = new DiffUtil.ItemCallback<BagProductModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull BagProductModel oldItem, @NonNull BagProductModel newItem) {
            return oldItem.getImg_url().equals(newItem.getImg_url());
        }

        @Override
        public boolean areContentsTheSame(@NonNull BagProductModel oldItem, @NonNull BagProductModel newItem) {
            return oldItem.equals(newItem);
        }
    };
    @BindingAdapter("android:bagImage")
    public static void loadImage (ImageView imageView, String imageUrl) {
        Glide.with(imageView)
                .load(imageUrl)
                .fitCenter()
                .into(imageView);
    }

}
