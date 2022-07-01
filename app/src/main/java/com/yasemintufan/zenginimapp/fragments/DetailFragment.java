package com.yasemintufan.zenginimapp.fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
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
import com.yasemintufan.zenginimapp.viewModels.CarViewModel;
import com.yasemintufan.zenginimapp.viewModels.HomeViewModel;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class DetailFragment extends Fragment {

    FragmentDetailBinding fragmentDetailBinding;
    CarViewModel carViewModel;

    public DetailFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false);
        return fragmentDetailBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setCarViewModel();
    }
    private void setCarViewModel () {
        carViewModel = new ViewModelProvider(requireActivity()).get(CarViewModel.class);
        fragmentDetailBinding.setCarViewModel(carViewModel);

    }
}


