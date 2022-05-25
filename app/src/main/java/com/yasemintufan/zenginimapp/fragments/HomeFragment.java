package com.yasemintufan.zenginimapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.yasemintufan.zenginimapp.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


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

        slideModels.add(new SlideModel(R.drawable.araba,"LUXURY CARS", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.saat,"CUSTOM DESİGNED WATCHES", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.canta,"BRILLIANT COLORS", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        return view;
    }
}