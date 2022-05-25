package com.yasemintufan.zenginimapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.yasemintufan.zenginimapp.R;
import com.yasemintufan.zenginimapp.fragments.BasketFragment;
import com.yasemintufan.zenginimapp.fragments.HomeFragment;
import com.yasemintufan.zenginimapp.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    Fragment homeFragment,searchFragment,basketFragment;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        homeFragment = new HomeFragment();
        loadFragment(homeFragment);
    }

    private void loadFragment(Fragment homeFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container,homeFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       int id = item.getItemId();
       if (id == R.id.menu_search) {
           searchFragment = new SearchFragment();
           loadFragment(searchFragment);


       } else if (id == R.id.menu_shopping) {
           basketFragment = new BasketFragment();
           loadFragment(basketFragment);


       }
       return true;
    }

}