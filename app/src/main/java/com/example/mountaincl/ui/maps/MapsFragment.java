package com.example.mountaincl.ui.maps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mountaincl.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import static com.example.mountaincl.helper.URLS.URL_MAPS;
import static java.lang.String.valueOf;

public class MapsFragment extends Fragment {

    MapLocations mapLocations;
    private Marker previousMarker = null;
    private MapsViewModel mViewModel;
    private RequestQueue mQueue;
    private MapAdapter mapAdapter;
    List<MapLocations> mapLocationsList = new ArrayList<>();



    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {

            LatLng edinburgh = new LatLng(55.9533, -3.1883);


            mQueue = Volley.newRequestQueue(getContext());
            String url = URL_MAPS;
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("mapLocations");

                        for(int i = 0; i<jsonArray.length(); i++){
                            JSONObject mapLocations = jsonArray.getJSONObject(i);

                            String name = mapLocations.getString("name");
                            double lat = mapLocations.getDouble("positionX");
                            double lon = mapLocations.getDouble("positionY");
                            String description = mapLocations.getString("description");
                            String image = mapLocations.getString("image");

                            System.out.println("name: "+name+", positionX: "+lat+", positionY: "+lon);
                            LatLng city = new LatLng(lat,lon);

                            mapLocationsList.add(new MapLocations(name,lat, lon,description,image));
                                    googleMap.addMarker(new MarkerOptions().position(city).title(name));

                            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker){
                                    //int position = currentMarkers.indexOf(marker);
                                    String locAddress = marker.getTitle();
                                    //viewPager.setCurrentItem(position, true);
                                    Toast.makeText(getContext(), "hello there", Toast.LENGTH_SHORT).show();
                                    marker.showInfoWindow();

                                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                                    previousMarker = marker;
                                    return true;
                                }
                            });

                        }


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


            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            googleMap.getUiSettings().setCompassEnabled(true);
            googleMap.getUiSettings().setMapToolbarEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.getUiSettings().setZoomGesturesEnabled(true);

            googleMap.addMarker(new MarkerOptions().position(edinburgh).title("Edinburgh"));


            googleMap.moveCamera(CameraUpdateFactory.newLatLng(edinburgh));
        }

    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MapsViewModel.class);
        // TODO: Use the ViewModel
    }


}