package com.yasemintufan.zenginimapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yasemintufan.zenginimapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void signİn(View view) {

        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }
    public void signUp(View view) {

        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
    }
}