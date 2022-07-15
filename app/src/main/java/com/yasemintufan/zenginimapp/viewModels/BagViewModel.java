package com.yasemintufan.zenginimapp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yasemintufan.zenginimapp.fragments.BagFragment;
import com.yasemintufan.zenginimapp.models.BagProductModel;
import com.yasemintufan.zenginimapp.repository.BagRepository;

import java.util.ArrayList;
import java.util.List;

public class BagViewModel extends ViewModel {

    BagRepository bagRepository = new BagRepository();

    MutableLiveData <BagProductModel> mutableBag = new MutableLiveData<>();

    public LiveData<List<BagProductModel>> getBagProduct() {

        return bagRepository.getBagProduct();

    }
    public void setBagProductModel(BagProductModel bagProductModel) {

        mutableBag.setValue(bagProductModel);
    }
    public LiveData <BagProductModel> getBag() {

        return mutableBag;
    }

}
