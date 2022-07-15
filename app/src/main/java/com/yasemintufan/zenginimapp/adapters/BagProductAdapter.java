package com.yasemintufan.zenginimapp.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.yasemintufan.zenginimapp.databinding.BagProductBinding;
import com.yasemintufan.zenginimapp.models.BagProductModel;

public class BagProductAdapter extends ListAdapter<BagProductModel,BagProductAdapter.BagViewHolder> {
      BagInterface bagInterface;
    public BagProductAdapter(BagInterface bagInterface) {
        super(BagProductModel.itemCallback);
        this.bagInterface = bagInterface;
    }
    @NonNull
    @Override
    public BagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        BagProductBinding bagProductBinding = BagProductBinding.inflate(layoutInflater,parent,false);
        bagProductBinding.setBagInterface(bagInterface);
        return new BagViewHolder(bagProductBinding);
    }
    @Override
    public void onBindViewHolder(@NonNull BagViewHolder holder, int position) {

        BagProductModel bagProductModel = getItem(position);
        holder.bagProductBinding.setBagProductModel(bagProductModel);

    }
    class BagViewHolder extends RecyclerView.ViewHolder {

        BagProductBinding bagProductBinding;
        public BagViewHolder(BagProductBinding binding) {
            super(binding.getRoot());
            this.bagProductBinding = binding;

        }
    }
    public interface BagInterface {

        void addItem (BagProductModel bagProductModel);
        void onItemClick (BagProductModel bagProductModel);


    }
}
