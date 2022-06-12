package com.yasemintufan.zenginimapp.fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.yasemintufan.zenginimapp.DataLoadListener;
import com.yasemintufan.zenginimapp.R;
import com.yasemintufan.zenginimapp.adapters.NewProductsAdapter;
import com.yasemintufan.zenginimapp.databinding.FragmentDetailBinding;
import com.yasemintufan.zenginimapp.models.NewProductsModel;
import com.yasemintufan.zenginimapp.viewModels.HomeViewModel;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class DetailFragment extends Fragment {

    ImageView detailImg;
    TextView rating,name,description,price;
    Button addToCart,buyNow;
    ImageView addItems,removeItems;

    FirebaseFirestore firebaseFirestore;
    NewProductsModel newProductsModel;
    private String string;
    public DetailFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view =  inflater.inflate(R.layout.fragment_detail, container, false);




         firebaseFirestore = FirebaseFirestore.getInstance();


         detailImg = view.findViewById(R.id.detail_img);
        name = view.findViewById(R.id.detail_name);
        description = view.findViewById(R.id.detail_desc);
        price = view.findViewById(R.id.detail_price);
        addToCart = view.findViewById(R.id.add_to_cart);
        buyNow = view.findViewById(R.id.buy_now);
        addItems = view.findViewById(R.id.add_item);
        removeItems = view.findViewById(R.id.remove_item);





        return view;
    }

}


