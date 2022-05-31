package com.yasemintufan.zenginimapp.adapters;

import android.content.Context;
import android.gesture.GestureLibraries;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yasemintufan.zenginimapp.R;
import com.yasemintufan.zenginimapp.models.NewProductsModel;

import java.util.List;

public class NewProductsAdapter extends RecyclerView.Adapter<NewProductsAdapter.ViewHolder> {


   private Context context;
   private List <NewProductsModel> newProductsModels;

    public NewProductsAdapter(Context context, List<NewProductsModel> newProductsModels) {
        this.context = context;
        this.newProductsModels = newProductsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_products,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(newProductsModels.get(position).getImg_url()).into(holder.newImg);
        holder.newName.setText(newProductsModels.get(position).getName());
        holder.newPrice.setText(String.valueOf(newProductsModels.get(position).getPrice()));



    }

    @Override
    public int getItemCount() {
        return newProductsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView newImg;
        TextView newName,newPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newImg = itemView.findViewById(R.id.new_img);
            newName = itemView.findViewById(R.id.new_product_name);
            newPrice = itemView.findViewById(R.id.new_price);

        }
    }
}
