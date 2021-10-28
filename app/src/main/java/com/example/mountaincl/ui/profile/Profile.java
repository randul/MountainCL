package com.example.mountaincl.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mountaincl.R;
import com.example.mountaincl.helper.PrefManager;
import com.example.mountaincl.helper.User;
import com.google.android.material.navigation.NavigationView;

public class Profile extends AppCompatActivity {

    TextView textViewId, textViewUsername, textViewEmail, textViewPhone;
    ImageView profileImage, navigationImage;
    String userID,userName,userEmail;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        navigationImage = findViewById(R.id.imageMenuProfile);



        profileImage = findViewById(R.id.profileImage);
        textViewId = findViewById(R.id.profileUserId);
        textViewUsername = findViewById(R.id.profileUserName);
        textViewEmail = findViewById(R.id.profileUserEmail);
        textViewPhone = findViewById(R.id.profileUserPhone);

        //getting the current user
        User user = PrefManager.getInstance(this).getUser();

        userName = user.getUsername();
        System.out.println(userName);

        textViewId.setText(String.valueOf(user.getId()));
        textViewUsername.setText(user.getUsername());
        textViewEmail.setText(user.getEmail());
        textViewPhone.setText(user.getPhone());

        navigationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}