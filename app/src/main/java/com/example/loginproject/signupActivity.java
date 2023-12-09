package com.example.loginproject;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signupActivity extends AppCompatActivity {

    EditText name, username, password;
    Button signup;
    TextView login;
    FirebaseAuth firebaseAuth;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });
        name= findViewById(R.id.et_name);
        username= findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);
        signup= findViewById(R.id.signup_btn);
        login=findViewById(R.id.login_tv);
        firebaseAuth= FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(signupActivity.this,loginActivity.class);
                startActivity(i);
                finish();
            }
        });
        
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etname= name.getText().toString().trim();
                String etusername= username.getText().toString().trim();
                String etpass=password.getText().toString().trim();
                if (etname.isEmpty()) {
                    name.setError("Required");
                    return;
                }
                if (etusername.isEmpty()) {
                    username.setError("Required");
                    return;
                }
                if (etpass.isEmpty()) {
                    password.setError("Required");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(etusername).matches()) {
                    username.setError("Invalid Email Format");
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(etusername,etpass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent i= new Intent(signupActivity.this,MainActivity.class);
                                    startActivity(i);
                                    finish();
                                    Toast.makeText(signupActivity.this, "Thanks for being awesome!", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(signupActivity.this, "Error:"+task.getException(), Toast.LENGTH_LONG).show();
                                    task.getException();
                                }
                            }
                        });

            }

        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent( signupActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(signupActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }
}
