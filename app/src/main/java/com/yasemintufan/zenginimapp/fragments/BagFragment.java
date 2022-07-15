package com.yasemintufan.zenginimapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasemintufan.zenginimapp.R;
import com.yasemintufan.zenginimapp.adapters.BagProductAdapter;
import com.yasemintufan.zenginimapp.databinding.FragmentBagBinding;
import com.yasemintufan.zenginimapp.models.BagProductModel;
import com.yasemintufan.zenginimapp.viewModels.BagViewModel;

import java.util.ArrayList;
import java.util.List;

public class BagFragment extends Fragment implements BagProductAdapter.BagInterface {

    FragmentBagBinding fragmentBagBinding;
    private BagProductAdapter bagProductAdapter;
    private BagViewModel bagViewModel;
    private static final String TAG = "BagFragment";

    public BagFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentBagBinding = FragmentBagBinding.inflate(inflater,container,false);
        return fragmentBagBinding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setBagRecyclerView();
        initBagViewModel();

    }
    private void setBagRecyclerView() {

        bagProductAdapter = new BagProductAdapter(this);
        fragmentBagBinding.bagRecyclerView.setAdapter(bagProductAdapter);
        fragmentBagBinding.bagRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));
        fragmentBagBinding.bagRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL));
    }
    private void initBagViewModel(){

        bagViewModel = new ViewModelProvider(requireActivity()).get(BagViewModel.class);
        bagViewModel.getBagProduct().observe(getViewLifecycleOwner(), new Observer<List<BagProductModel>>() {
            @Override
            public void onChanged(List<BagProductModel> bagProductModels) {
                bagProductAdapter.submitList(bagProductModels);
            }
        });
    }
    @Override
    public void addItem(BagProductModel bagProductModel) {

    }
    @Override
    public void onItemClick(BagProductModel bagProductModel) {
        Log.d(TAG,"onItemClick" + bagProductModel.toString());
        bagViewModel.setBagProductModel(bagProductModel);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new DetailFragment()).commit();

    }
}