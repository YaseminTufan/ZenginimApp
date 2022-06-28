package com.yasemintufan.zenginimapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.yasemintufan.zenginimapp.R;
import com.yasemintufan.zenginimapp.fragments.BagFragment;
import com.yasemintufan.zenginimapp.fragments.BasketFragment;
import com.yasemintufan.zenginimapp.fragments.CarFragment;
import com.yasemintufan.zenginimapp.fragments.HomeFragment;
import com.yasemintufan.zenginimapp.fragments.SearchFragment;
import com.yasemintufan.zenginimapp.fragments.WatchFragment;
import com.yasemintufan.zenginimapp.models.BasketItem;
import com.yasemintufan.zenginimapp.viewModels.CarViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Fragment homeFragment,searchFragment,basketFragment;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private TextView textName;
    CarViewModel carViewModel;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        setHomeFragment();
        setToolbar();
        setNavigationDrawer();
        setNavigationView();
        appNameClick();
        carViewModel = new ViewModelProvider(this).get(CarViewModel.class);
        carViewModel.getBasket().observe(this, new Observer<List<BasketItem>>() {
            @Override
            public void onChanged(List<BasketItem> basketItems) {
                Log.d(TAG,"onChanged: " + basketItems.size());
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_car:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new CarFragment()).commit();
                break;
            case R.id.nav_watch:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new WatchFragment()).commit();
                break;
            case R.id.nav_bag:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new BagFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
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

    private void setHomeFragment () {

        homeFragment = new HomeFragment();
        loadFragment(homeFragment);

    }
    private void initComponents () {

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        textName = findViewById(R.id.text_name);

    }
    private void setToolbar () {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

    }
    private void setNavigationView() {

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setNavigationDrawer () {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }
    private void appNameClick() {

        textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

}