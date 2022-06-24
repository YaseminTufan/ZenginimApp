package com.yasemintufan.zenginimapp.models;

import java.util.Objects;

public class BasketItem {

    private CarProductModel carProductModel;
    private int quantity;

    public BasketItem(CarProductModel carProductModel, int quantity) {
        this.carProductModel = carProductModel;
        this.quantity = quantity;
    }

    public CarProductModel getCarProductModel() {
        return carProductModel;
    }

    public void setCarProductModel(CarProductModel carProductModel) {
        this.carProductModel = carProductModel;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "carProductModel=" + carProductModel +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem that = (BasketItem) o;
        return getQuantity() == that.getQuantity() && getCarProductModel().equals(that.getCarProductModel());
    }

}
