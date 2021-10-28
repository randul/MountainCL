package com.example.mountaincl.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mountaincl.MainActivity;
import com.example.mountaincl.R;
import com.example.mountaincl.helper.PrefManager;
import com.example.mountaincl.ui.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    //we use a splash time out to show splash screen
    //the timer was set to  last for 3 seconds
    private static int SPLASH_TIME_OUT = 7000;

    TextView title, subtitle;
    ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        //using prefManager we check if user logged
        PrefManager prefManager = PrefManager.getInstance(SplashScreen.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //if user has logged in then we go to the main activity, else, he goes to login screen
                if(prefManager.isLoggedIn()){
                    Intent homeIntent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();

                }else{
                    Intent loginIntent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}
