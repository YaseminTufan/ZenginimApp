package com.yasemintufan.zenginimapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.yasemintufan.zenginimapp.databinding.BasketRowBinding;
import com.yasemintufan.zenginimapp.models.BasketItem;

public class BasketListAdapter extends ListAdapter<BasketItem, BasketListAdapter.BasketVH> {
      private BasketInterface basketInterface;
    public BasketListAdapter(BasketInterface basketInterface) {
        super(BasketItem.itemCallback);
        this.basketInterface = basketInterface;
    }

    @NonNull
    @Override
    public BasketVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        BasketRowBinding basketRowBinding = BasketRowBinding.inflate(layoutInflater,parent,false);
        return new BasketVH(basketRowBinding);
    }
    @Override
    public void onBindViewHolder(@NonNull BasketVH holder, int position) {

        holder.basketRowBinding.setBasketItem(getItem(position));
        holder.basketRowBinding.executePendingBindings();
    }
    class BasketVH extends RecyclerView.ViewHolder {

        BasketRowBinding basketRowBinding;
        public BasketVH(@NonNull BasketRowBinding basketRowBinding) {
            super(basketRowBinding.getRoot());
            this.basketRowBinding = basketRowBinding;

            basketRowBinding.deleteProductButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    basketInterface.deleteItem(getItem(getAdapterPosition()));
                }
            });
        }
    }
    public interface BasketInterface {
        void deleteItem(BasketItem basketItem);
    }
}
