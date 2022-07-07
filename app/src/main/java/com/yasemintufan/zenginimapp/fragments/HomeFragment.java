package com.yasemintufan.zenginimapp.fragments;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.yasemintufan.zenginimapp.DataLoadListener;
import com.yasemintufan.zenginimapp.R;
import com.yasemintufan.zenginimapp.adapters.NewProductsAdapter;
import com.yasemintufan.zenginimapp.adapters.PopularProductAdapter;
import com.yasemintufan.zenginimapp.models.CarProductModel;
import com.yasemintufan.zenginimapp.models.NewProductsModel;
import com.yasemintufan.zenginimapp.models.PopularProductModel;
import com.yasemintufan.zenginimapp.viewModels.HomeViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements DataLoadListener {

    RecyclerView newProductRecyclerview,popularRecyclerview;
    NewProductsAdapter newProductsAdapter;
    PopularProductAdapter popularProductAdapter;
    List<PopularProductModel> popularProductModelList;
    HomeViewModel homeViewModel;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    LinearLayout linearLayout;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_home, container, false);

        initComponents(view);
        setNewProductRecyclerview();
        imageSlider(view);
        initHomeViewModel();
        setProgressDialog();
        linearLayoutVisibility();
        setPopularRecyclerview(view);
        popularFirebaseFirestore();

        return view;
    }
    private void initHomeViewModel () {
        //New Products

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.init(HomeFragment.this);

    }
    @Override
    public void onNameLoaded() {
        homeViewModel.getNewProducts().observe(this, new Observer<ArrayList<NewProductsModel>>() {
            @Override
            public void onChanged(ArrayList<NewProductsModel> newProductsModels) {
                newProductsAdapter = new NewProductsAdapter(getContext(), newProductsModels);
                newProductRecyclerview.setAdapter(newProductsAdapter);
                newProductsAdapter.notifyDataSetChanged();
            }
        });
    }
    private void initComponents(View view) {

        newProductRecyclerview = view.findViewById(R.id.new_product_rec);
        linearLayout = view.findViewById(R.id.home_layout);
        popularRecyclerview = view.findViewById(R.id.popular_rec);

    }
    private void setNewProductRecyclerview () {

         //NewProduct Recyclerview
        newProductRecyclerview.setHasFixedSize(true);
        newProductRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));

    }
    private  void setPopularRecyclerview (View view) {

        //PopularRecyclerview

        popularRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularProductModelList = new ArrayList<>();
        popularProductAdapter = new PopularProductAdapter(getContext(),popularProductModelList);
        popularRecyclerview.setHasFixedSize(true);
        popularRecyclerview.setAdapter(popularProductAdapter);

    }
    private void imageSlider (View view) {

        //Image slider
        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.araba,"LUXURY CARS", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.saat,"CUSTOM DESİGNED WATCHES", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.canta,"BRILLIANT COLORS", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels);

    }
    private void setProgressDialog() {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Welcome To My ECommerce App");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

    }
    private void linearLayoutVisibility() {

        linearLayout.setVisibility(View.GONE);
    }

    private void popularFirebaseFirestore() {
            firebaseFirestore = firebaseFirestore.getInstance();

            firebaseFirestore.collection("PopularProduct")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    PopularProductModel popularProductModel = (PopularProductModel) document.toObject(PopularProductModel.class);
                                    popularProductModelList.add(popularProductModel);
                                    popularProductAdapter.notifyDataSetChanged();
                                    linearLayout.setVisibility(View.VISIBLE);
                                    progressDialog.dismiss();
                                }
                            }else {
                                Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    }
}