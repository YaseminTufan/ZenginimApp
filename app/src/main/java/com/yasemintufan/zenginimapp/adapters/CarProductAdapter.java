package com.yasemintufan.zenginimapp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.yasemintufan.zenginimapp.R;
import com.yasemintufan.zenginimapp.databinding.CarProductBinding;
import com.yasemintufan.zenginimapp.databinding.FragmentCarBinding;
import com.yasemintufan.zenginimapp.fragments.CarFragment;
import com.yasemintufan.zenginimapp.fragments.DetailFragment;
import com.yasemintufan.zenginimapp.models.CarProductModel;

public class CarProductAdapter extends ListAdapter<CarProductModel,CarProductAdapter.CarViewHolder> {
     CarInterface carInterface;

    public CarProductAdapter(CarInterface carInterface) {
        super(CarProductModel.itemCallback);
        this.carInterface = carInterface;
    }
    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CarProductBinding carProductBinding = CarProductBinding.inflate(layoutInflater,parent,false);
        carProductBinding.setCarInterface(carInterface);
        return new CarViewHolder(carProductBinding);
    }
    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {

        CarProductModel carProductModel = getItem(position);
        holder.carProductBinding.setCarProductModel(carProductModel);
        holder.carProductBinding.executePendingBindings();
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
