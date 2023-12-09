package com.example.loginproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class dashboardActivity extends AppCompatActivity {
    Button bt1,bt6;
    ImageButton bt3,bt4,bt5,bt7;

    CardView cardHome,cardChat,cardProfile,cardWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bt1=findViewById(R.id.verify_button);
        bt3=findViewById(R.id.wt_button);
        bt4=findViewById(R.id.call_button);
        bt5=findViewById(R.id.email_button);
        cardHome = findViewById(R.id.cardHome);
        bt6=findViewById(R.id.lgt_btn);

        cardChat = findViewById(R.id.cardChat);
        cardProfile = findViewById(R.id.cardProfile);
        cardWidget = findViewById(R.id.cardWidget);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("mail sent to your inbox");
            }
        });


        cardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(" Working Properly");
            }
        });
        cardChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Working Proerly");
            }
        });
        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Working Proerly");
            }
        });
        cardWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Working Proerly");

            }
        });
       bt3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // Performs action on click
               Intent sendIntent = new Intent();
               sendIntent.setAction(Intent.ACTION_SEND);
               sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
               sendIntent.setType("text/plain");
               sendIntent.setPackage("com.whatsapp");
               startActivity(Intent.createChooser(sendIntent, ""));
               startActivity(sendIntent);
               //opens the portfolio details class
           }
       });
       bt4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String phoneNumber ="tel:03000000000";

               Intent intent = new Intent(Intent.ACTION_CALL);
               Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber)) ;
               startActivity(i);
           }
       });
       bt5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_SEND);
               intent.setType("text/html");
               intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
               intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
               intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
               startActivity(Intent.createChooser(intent, "Send Email"));
           }
       });
       bt6.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(dashboardActivity.this,loginActivity.class);
               startActivity(i);
               finish();
           }
       });


    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }}
