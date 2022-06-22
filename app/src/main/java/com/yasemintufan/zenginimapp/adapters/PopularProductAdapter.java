package com.yasemintufan.zenginimapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yasemintufan.zenginimapp.R;
import com.yasemintufan.zenginimapp.models.PopularProductModel;

import org.w3c.dom.Text;

import java.util.List;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.ViewHolder> {

    private Context context;
    private List<PopularProductModel>popularProductModelList;

    public PopularProductAdapter(Context context, List<PopularProductModel> popularProductModelList) {
        this.context = context;
        this.popularProductModelList = popularProductModelList;
    }
    @NonNull
    @Override
    public PopularProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(popularProductModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(popularProductModelList.get(position).getName());
        holder.price.setText(String.valueOf(popularProductModelList.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return popularProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pop_img);
            name = itemView.findViewById(R.id.new_product_name);
            price = itemView.findViewById(R.id.new_price);
        }
    }
}
