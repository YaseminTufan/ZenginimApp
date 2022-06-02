package com.yasemintufan.zenginimapp.viewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yasemintufan.zenginimapp.fragments.HomeFragment;
import com.yasemintufan.zenginimapp.models.NewProductsModel;
import com.yasemintufan.zenginimapp.repository.HomeRepository;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    MutableLiveData<ArrayList<NewProductsModel>> newProducts;

    public void init (HomeFragment context) {
        if (newProducts!=null) {
            return;
        }
        newProducts = HomeRepository.getInstance(context).getNewProducts();

    }
    public LiveData<ArrayList<NewProductsModel>> getNewProducts () {
        return newProducts;
    }
}
