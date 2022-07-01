package com.yasemintufan.zenginimapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yasemintufan.zenginimapp.models.BasketItem;
import com.yasemintufan.zenginimapp.models.CarProductModel;

import java.util.ArrayList;
import java.util.List;

public class BasketRepository {

    private MutableLiveData <List<BasketItem>> mutableBasket = new MutableLiveData<>();
    private MutableLiveData <Double> mutableTotalPrice = new MutableLiveData<>();

    public LiveData<List<BasketItem>> getBasket () {

        if (mutableBasket.getValue() == null) {
            initBasket();
        }
        return mutableBasket;
    }
    public void initBasket() {
        mutableBasket.setValue(new ArrayList<BasketItem>());
        calculateBasketTotal();
    }
    public boolean addItemToCart (CarProductModel carProductModel) {
        if (mutableBasket.getValue() == null) {
            initBasket();
        }
        List<BasketItem> basketItemList = new ArrayList<>(mutableBasket.getValue());
        for (BasketItem basketItem: basketItemList) {
            if (basketItem.getCarProductModel().getName().equals(carProductModel.getName())) {
                if (basketItem.getQuantity() == 5) {
                    return false;
                }
                int index = basketItemList.indexOf(basketItem);
                basketItem.setQuantity(basketItem.getQuantity() + 1);
                basketItemList.set(index,basketItem);

                mutableBasket.setValue(basketItemList);
                calculateBasketTotal();

                return true;
            }
        }

        BasketItem basketItem = new BasketItem(carProductModel,1);
        basketItemList.add(basketItem);
        mutableBasket.setValue(basketItemList);
        calculateBasketTotal();
        return true;
    }
    public void removeItemFromBasket(BasketItem basketItem) {
        if (mutableBasket.getValue() == null) {
            return;
        }
        List<BasketItem> basketItemList = new ArrayList<>(mutableBasket.getValue());

        basketItemList.remove(basketItem);
        mutableBasket.setValue(basketItemList);
        calculateBasketTotal();
    }
    public void changeQuantity(BasketItem basketItem, int quantity) {
        if (mutableBasket.getValue() == null) return;

        List <BasketItem> basketItemList = new ArrayList<>(mutableBasket.getValue());
        BasketItem updatedItem = new BasketItem(basketItem.getCarProductModel(), quantity);
        basketItemList.set(basketItemList.indexOf(basketItem), updatedItem);

        mutableBasket.setValue(basketItemList);
        calculateBasketTotal();
    }
    private void calculateBasketTotal () {
        if (mutableBasket.getValue() == null) return;
        double total = 0.0;
        List <BasketItem> basketItemList = mutableBasket.getValue();
        for (BasketItem basketItem: basketItemList) {
            total += basketItem.getCarProductModel().getPrice() * basketItem.getQuantity();
        }
        mutableTotalPrice.setValue(total);
    }
    public LiveData <Double> getTotalPrice () {
        if (mutableTotalPrice.getValue() == null) {
            mutableTotalPrice.setValue(0.0);
        }
        return mutableTotalPrice;
    }
}
