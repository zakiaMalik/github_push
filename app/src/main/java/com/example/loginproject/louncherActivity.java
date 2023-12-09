package com.example.loginproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class louncherActivity extends AppCompatActivity {
    FirebaseAuth mAuth ;
    FirebaseUser user ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_louncher);
        Handler handler= new Handler();
        mAuth = FirebaseAuth.getInstance();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent i = new Intent(louncherActivity.this, loginActivity.class);
                    startActivity(i);
                }


        },800 );
    }
}