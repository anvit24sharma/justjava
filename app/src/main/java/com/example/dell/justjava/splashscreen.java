package com.example.dell.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;


import android.widget.ImageView;

public class splashscreen extends AppCompatActivity {



    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

            // Splash screen timer

                new Handler().postDelayed(new Runnable() {
                public void run() {
                        Intent i = new Intent(splashscreen.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 3000);
        imageView = (ImageView) findViewById(R.id.imageview);


        }
}

