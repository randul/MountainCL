package com.example.mountaincl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mountaincl.helper.PrefManager;
import com.example.mountaincl.helper.User;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    TextView userNav_name, userNav_email;
    ImageView userNav_image;
    NavigationView navigationView;
    String actualUserName, actualUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrefManager prefManager = PrefManager.getInstance(MainActivity.this);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        View header = navigationView.getHeaderView(0);

        userNav_name = header.findViewById(R.id.userNameNavHeader);
        userNav_email = header.findViewById(R.id.userEmailNavHeader);
        userNav_image = header.findViewById(R.id.imageViewNavBar);


        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });



//In case the pref manager shows that user is logged we change the visibility of some items from the menu
        if(prefManager.isLoggedIn()) {
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.menuLogin).setVisible(false);
            nav_Menu.findItem(R.id.menuRegister).setVisible(false);

            nav_Menu.findItem(R.id.menuChangePass).setVisible(true);
            nav_Menu.findItem(R.id.menuLogOut).setVisible(true);



            userNav_name.setText("my name");
            //we also get the user ID and name and display it on the navigation drawer
            actualUserName = prefManager.getUser().getUsername();
            actualUserEmail = prefManager.getUser().getEmail();
            System.out.println(actualUserName);
            //actualUserName = userNav_name.getText().toString();
            userNav_name.setText(actualUserName);
            userNav_email.setText(actualUserEmail);
        }

        NavigationUI.setupWithNavController(navigationView,navController);

    }

}