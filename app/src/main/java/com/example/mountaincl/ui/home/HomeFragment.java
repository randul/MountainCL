package com.example.mountaincl.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mountaincl.R;
import com.example.mountaincl.helper.PrefManager;
import com.example.mountaincl.ui.equipment.EquipmentActivity;
import com.example.mountaincl.ui.faq.FrequentQuestions;
import com.example.mountaincl.ui.faq.Questions;
import com.example.mountaincl.ui.imageEditor.ImageEditor;
import com.example.mountaincl.ui.profile.Profile;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;

    CardView equipment, favorites, social, profile, routeCreator;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        CardView equipment = root.findViewById(R.id.equipmentCardView);
        CardView favorites = root.findViewById(R.id.favoritesCardView);
        CardView social = root.findViewById(R.id.socialCardView);
        CardView routeCreator = root.findViewById(R.id.editorCardView);
        CardView profile = root.findViewById(R.id.profileCardView);

        PrefManager prefManager = PrefManager.getInstance(getContext());


        if(prefManager.isLoggedIn()) {
            profile.setVisibility(View.VISIBLE);
        }


        equipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EquipmentActivity.class));
            }
        });

        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Not supported yet", Toast.LENGTH_SHORT).show();
            }
        });

        social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FrequentQuestions.class));
            }
        });


        routeCreator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ImageEditor.class));
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Profile.class));
            }
        });

        return root;
    }
}