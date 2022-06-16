package com.yasemintufan.zenginimapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasemintufan.zenginimapp.R;
import com.yasemintufan.zenginimapp.adapters.CarProductAdapter;
import com.yasemintufan.zenginimapp.databinding.FragmentCarBinding;
import com.yasemintufan.zenginimapp.models.CarProductModel;
import com.yasemintufan.zenginimapp.viewModels.CarViewModel;

import java.util.List;

public class CarFragment extends Fragment implements CarProductAdapter.CarInterface {

    FragmentCarBinding fragmentCarBinding;
   private CarProductAdapter carProductAdapter;
    private CarViewModel carViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCarBinding = FragmentCarBinding.inflate(inflater,container,false);
        return fragmentCarBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        carProductAdapter = new CarProductAdapter();
        fragmentCarBinding.carRecyclerView.setAdapter(carProductAdapter);

        carViewModel = new ViewModelProvider(requireActivity()).get(CarViewModel.class);
        carViewModel.getCarModel().observe(getViewLifecycleOwner(), new Observer<List<CarProductModel>>() {
            @Override
            public void onChanged(List<CarProductModel> carProductModels) {
                carProductAdapter.submitList(carProductModels);
            }
        });
    }

    @Override
    public void addItem(CarProductModel carProductModel) {

    }

    @Override
    public void onItemClick(CarProductModel carProductModel) {

    }
}