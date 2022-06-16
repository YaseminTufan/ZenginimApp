package com.yasemintufan.zenginimapp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.yasemintufan.zenginimapp.models.CarProductModel;
import com.yasemintufan.zenginimapp.repository.CarRepository;

import java.util.List;

public class CarViewModel extends ViewModel {

    CarRepository carRepository = new CarRepository();

    public LiveData<List<CarProductModel>> getCarModel() {
        return carRepository.getCarModel();

    }
}
