package com.yasemintufan.zenginimapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.yasemintufan.zenginimapp.databinding.CarProductBinding;
import com.yasemintufan.zenginimapp.models.CarProductModel;

public class CarProductAdapter extends ListAdapter<CarProductModel,CarProductAdapter.CarViewHolder> {

    public CarProductAdapter() {
        super(CarProductModel.itemCallback);
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CarProductBinding carProductBinding = CarProductBinding.inflate(layoutInflater,parent,false);
        return new CarViewHolder(carProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        CarProductModel carProductModel = getItem(position);
        holder.carProductBinding.setCarProductModel(carProductModel);

    }

    class CarViewHolder extends RecyclerView.ViewHolder {

        CarProductBinding carProductBinding;

        public CarViewHolder(CarProductBinding binding) {
            super(binding.getRoot());
            this.carProductBinding = binding;
        }
    }
    public interface CarInterface {
        void addItem (CarProductModel carProductModel);
        void onItemClick (CarProductModel carProductModel);
    }
}
