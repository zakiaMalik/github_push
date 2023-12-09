package com.example.loginproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginproject.databinding.ActivityMainBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    EditText et1, et2;
    Button bt1;
    TextView tv1,tv2;
    CheckBox checkBox;
    private FirebaseAuth mAuth;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        adView=findViewById(R.id.adView);
        AdRequest adRequest =new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        et1 = findViewById(R.id.username);
        et2 = findViewById(R.id.password);
        bt1 = findViewById(R.id.login_btn);
        tv1 = findViewById(R.id.signup_tv);
        tv2=findViewById(R.id.terms);
        checkBox=findViewById(R.id.checkbox);
        mAuth=FirebaseAuth.getInstance();

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(loginActivity.this,termsActivity.class);
                startActivity(i);
            }
        });


        tv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(loginActivity.this, signupActivity.class);
                startActivity(i);
            }
    });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et1.getText().toString().trim();
                String password = et2.getText().toString().trim();
                if (username.isEmpty()) {
                    et1.setError("required");
                    return;
                }
                if (password.isEmpty()) {
                    et2.setError("required");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                    et1.setError("invalid email format");
                    return;
                }
                if(!checkBox.isChecked()) {
                    tv2.setError("Accept");
                    return;
                }



                mAuth.signInWithEmailAndPassword(username,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent i = new Intent(loginActivity.this,MainActivity.class);
                                    startActivity(i);

                                }
                                else{
                                    Toast.makeText(loginActivity. this,"email or password is incorrect ",Toast.LENGTH_SHORT).show();
                                }
                            }

}); }
        });}
}