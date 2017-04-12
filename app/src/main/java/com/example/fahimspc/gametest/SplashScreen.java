package com.example.fahimspc.gametest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public  class SplashScreen extends AppCompatActivity  {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


//        View decorView = getWindow().getDecorView();
//        // Hide the status bar.
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//
//        decorView.setSystemUiVisibility(uiOptions);


        try {
            this.getSupportActionBar().hide();
        } catch (Exception e){

        }
            Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1500);
                    Intent sintent = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(sintent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }

            }
        };
        thread.start();
    }


}