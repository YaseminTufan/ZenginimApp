package com.yasemintufan.zenginimapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.yasemintufan.zenginimapp.R;
import com.yasemintufan.zenginimapp.adapters.NewProductsAdapter;
import com.yasemintufan.zenginimapp.models.NewProductsModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RecyclerView newProductRecyclerview;
    NewProductsAdapter newProductsAdapter;
    List<NewProductsModel> newProductsModelList;
    FirebaseFirestore firebaseFirestore;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_home, container, false);
        // image slider
        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();

        newProductRecyclerview = view.findViewById(R.id.new_product_rec);

        firebaseFirestore = firebaseFirestore.getInstance();

        slideModels.add(new SlideModel(R.drawable.araba,"LUXURY CARS", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.saat,"CUSTOM DESİGNED WATCHES", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.canta,"BRILLIANT COLORS", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        //new Products
        newProductRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));
        newProductsModelList = new ArrayList<>();
        newProductsAdapter = new NewProductsAdapter(getContext(),newProductsModelList);
        newProductRecyclerview.setAdapter(newProductsAdapter);

       firebaseFirestore.collection("NewProduct")
               .get()
               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                
                                NewProductsModel newProductsModel = document.toObject(NewProductsModel.class);
                                newProductsModelList.add(newProductsModel);
                                newProductsAdapter.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        return view;
    }
}