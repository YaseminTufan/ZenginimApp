package com.yasemintufan.zenginimapp.fragments;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
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
    private static final String TAG = "CarFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentCarBinding = FragmentCarBinding.inflate(inflater,container,false);
        return fragmentCarBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setCarRecyclerView();
        initCarViewModel();
    }
    private void setCarRecyclerView() {

        carProductAdapter = new CarProductAdapter(this);
        fragmentCarBinding.carRecyclerView.setAdapter(carProductAdapter);
        fragmentCarBinding.carRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));
        fragmentCarBinding.carRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL));
    }
    private void initCarViewModel() {

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
        boolean isAdded = carViewModel.addCarToCart(carProductModel);
        if (isAdded) {
            Snackbar.make(requireView(),carProductModel.getName() + "added to basket.",Snackbar.LENGTH_LONG)
                    .setAction("Checkout", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            carViewModel.setCarProductModel(carProductModel);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new BasketFragment()).commit();

                        }
                    }).show();
        }else {
            Snackbar.make(requireView(),"Already have the max quantity in basket.",Snackbar.LENGTH_LONG)
                    .show();
        }
    }
    @Override
    public void onItemClick(CarProductModel carProductModel) {
        carViewModel.setCarProductModel(carProductModel);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new DetailFragment()).commit();
    }
}