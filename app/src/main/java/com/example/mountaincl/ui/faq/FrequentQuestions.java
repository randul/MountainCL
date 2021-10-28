package com.example.mountaincl.ui.faq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mountaincl.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.mountaincl.helper.URLS.URL_MAPS;
import static com.example.mountaincl.helper.URLS.URL_QUESTIONS;

public class FrequentQuestions extends AppCompatActivity {

    ImageView navigationImage;
    RecyclerView recyclerView;

    private List <Questions> list = new ArrayList<>();
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequent_questions);

        navigationImage = findViewById(R.id.imageMenuFrequentQuestion);
        //When we click on the navigationImage the activity closes and we go back to
        //the main activity
        navigationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //we use the recyclerView layout to create a list
        recyclerView = findViewById(R.id.recyclerView);
        //this sets the recyclerview to vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

       // Questions firstQuestions = new Questions ("Contact software support","there is no information on the software team");

        //list.add(firstQuestions);


        mQueue = Volley.newRequestQueue(this);
        String url = URL_QUESTIONS;



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {
                    JSONArray jsonArray = response.getJSONArray("Questions");



                        for(int i = 0; i<jsonArray.length(); i++) {
                            JSONObject questions = jsonArray.getJSONObject(i);

                            String title = questions.getString("title");
                            list.add(new Questions(title,"not content available yet"));
                           // list.add(new Questions(title,null));
                        }




                    System.out.println("look at : "+list.size());
                    System.out.println(list);

                    CustomAdapter customAdapter = new CustomAdapter(FrequentQuestions.this,list);
                    recyclerView.setAdapter(customAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);

    }

    public void onResume() {
        super.onResume();
        //Here we call the adapter and send the context and data
        CustomAdapter customAdapter = new CustomAdapter(FrequentQuestions.this,list);
        recyclerView.setAdapter(customAdapter);
    }
}