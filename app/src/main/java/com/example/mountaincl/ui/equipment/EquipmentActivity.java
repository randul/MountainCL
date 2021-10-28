package com.example.mountaincl.ui.equipment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.mountaincl.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EquipmentActivity extends AppCompatActivity {

    ImageView navigationImage;

    RecyclerView recyclerView;
    EquipmentAdapter equipmentAdapter;
    //List equipmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this sets the view for equipment activity
        setContentView(R.layout.activity_equipment);
        //this uses the navigation bar to add back button
        navigationImage = findViewById(R.id.imageMenuEquipment);
        //When we click on the navigationImage the activity closes and we go back to
        //the main activity
        navigationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Equipment[] equipmentList = new Equipment[]{
                new Equipment("Rope", "Used for ascending and descending", R.drawable.rope),
                new Equipment("Saguro Shoes", "Special Shoes used for climbing", R.drawable.saguaro_shoes),
                new Equipment("Climb Carabiners", "Used to hold 2 or more ropes together", R.drawable.climb_carabiners),
                new Equipment("Climb harness", "Used to secure ropes and screws", R.drawable.climb_harness),
                new Equipment("Rapel", "used for descending", R.drawable.oumers_rapel),
                new Equipment("Hand Ascenders", "Used as globes for easy Ascending", R.drawable.hand_ascender),
        };

        for(Equipment s: equipmentList){
            System.out.println(s);
        }

        System.out.println("--------------------------");
        System.out.println(equipmentList[0]);
        System.out.println("--------------------------");

        //we use the recyclerView layout to create a list
        recyclerView = findViewById(R.id.recyclerView);
        //this sets the recyclerview to vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //Here we call the adapter and send the context and data
        equipmentAdapter = new EquipmentAdapter(EquipmentActivity.this, equipmentList);
        //we set the adapter to the recyclerview
        recyclerView.setAdapter(equipmentAdapter);

        //print the data
        System.out.println(equipmentList);
    }

}
