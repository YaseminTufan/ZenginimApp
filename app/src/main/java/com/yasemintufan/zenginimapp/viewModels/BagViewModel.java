package com.yasemintufan.zenginimapp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yasemintufan.zenginimapp.fragments.BagFragment;
import com.yasemintufan.zenginimapp.models.BagProductModel;
import com.yasemintufan.zenginimapp.repository.BagRepository;

import java.util.ArrayList;

public class BagViewModel extends ViewModel {

    MutableLiveData<ArrayList<BagProductModel>> bagProducts;

    public void init (BagFragment context) {
        if (bagProducts != null) {
            return;
        }
        bagProducts = BagRepository.getInstance(context).getBagProduct();
    }
    public LiveData <ArrayList<BagProductModel>> getBagProduct() {

        return bagProducts;
    }
}
