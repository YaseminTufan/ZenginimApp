package com.yasemintufan.zenginimapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yasemintufan.zenginimapp.models.BasketItem;
import com.yasemintufan.zenginimapp.models.CarProductModel;

import java.util.ArrayList;
import java.util.List;

public class BasketRepository {

    private MutableLiveData<List<BasketItem>> mutableBasket = new MutableLiveData<>();

    public LiveData<List<BasketItem>> getBasket () {
        if (mutableBasket.getValue() == null) {
            initBasket();
        }
        return mutableBasket;
    }
    public void initBasket() {
        mutableBasket.setValue(new ArrayList<BasketItem>());
    }
    public boolean addItemToCart (CarProductModel carProductModel) {
        if (mutableBasket.getValue() == null) {
            initBasket();
        }
        List<BasketItem> basketItemList = new ArrayList<>(mutableBasket.getValue());

        BasketItem basketItem = new BasketItem(carProductModel,1);
        basketItemList.add(basketItem);
        mutableBasket.setValue(basketItemList);
        return true;
    }
}
