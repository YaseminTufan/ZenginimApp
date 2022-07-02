package com.yasemintufan.zenginimapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.api.LogDescriptor;
import com.yasemintufan.zenginimapp.R;
import com.yasemintufan.zenginimapp.adapters.BasketListAdapter;
import com.yasemintufan.zenginimapp.databinding.FragmentBagBinding;
import com.yasemintufan.zenginimapp.databinding.FragmentBasketBinding;
import com.yasemintufan.zenginimapp.databinding.FragmentCarBinding;
import com.yasemintufan.zenginimapp.models.BasketItem;
import com.yasemintufan.zenginimapp.viewModels.CarViewModel;

import java.util.List;

public class BasketFragment extends Fragment implements BasketListAdapter.BasketInterface{

    private static final String TAG = "BasketFragment";
    CarViewModel carViewModel;
    FragmentBasketBinding fragmentBasketBinding;

    public BasketFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       fragmentBasketBinding = FragmentBasketBinding.inflate(inflater,container,false);
       return fragmentBasketBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        basketRecyclerView();
        carGetTotalPrice();
    }
    @Override
    public void deleteItem(BasketItem basketItem) {
       carViewModel.removeItemFromBasket(basketItem);
    }
    @Override
    public void changeQuantity(BasketItem basketItem, int quantity) {
        carViewModel.changeQuantity(basketItem, quantity);
    }
    public void basketRecyclerView () {

        BasketListAdapter basketListAdapter = new BasketListAdapter(this);
        fragmentBasketBinding.basketRecyclerview.setAdapter(basketListAdapter);
        fragmentBasketBinding.basketRecyclerview.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));

        carViewModel = new ViewModelProvider(requireActivity()).get(CarViewModel.class);
        carViewModel.getBasket().observe(getViewLifecycleOwner(), new Observer<List<BasketItem>>() {
            @Override
            public void onChanged(List<BasketItem> basketItems) {

                basketListAdapter.submitList(basketItems);
                fragmentBasketBinding.orderButton.setEnabled(basketItems.size() > 0);
            }
        });
        fragmentBasketBinding.orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new OrderFragment()).commit();

            }
        });
    }
    public void carGetTotalPrice () {

        carViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentBasketBinding.totalTextView.setText("Total: $ "+ aDouble.toString());
            }
        });
    }
}