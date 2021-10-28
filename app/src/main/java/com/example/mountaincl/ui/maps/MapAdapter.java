package com.example.mountaincl.ui.maps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mountaincl.R;
import com.example.mountaincl.ui.faq.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.ViewHolder>{
    private List<MapLocations> mapLocations;
    private Context context;

    public MapAdapter (List<MapLocations>myLocations, Context ctx){
        this.mapLocations = myLocations;
        this.context = ctx;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView mapTitle;
        public TextView mapLocationLt;
        public TextView mapLocationLn;
        public TextView mapDescription;
        public LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            this.imageView = view.findViewById(R.id.imageViewAdapter);
            this.mapTitle = view.findViewById(R.id.textTitle);
            this.mapLocationLn = view.findViewById(R.id.textViewAdapterLocationLn);
            this.mapLocationLt = view.findViewById(R.id.textViewAdapterLocationLt);
            this.mapDescription = view.findViewById(R.id.textViewAdapterDescription);
            linearLayout = view.findViewById(R.id.relativeLayout);
        }
    }

    @NonNull
    @Override
    public MapAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the Layout(Instantiates list_item.xml
        // layout file into View object)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_show_map, parent, false);

        // Passing view to ViewHolder
        MapAdapter.ViewHolder viewHolder = new MapAdapter.ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MapAdapter.ViewHolder holder, final int position) {
        holder.mapTitle.setText(mapLocations.get(position).getLocationName());
        holder.mapLocationLn.setText((int) Double.parseDouble(String.valueOf(mapLocations.get(position).getLng())));
        holder.mapLocationLt.setText((int) Double.parseDouble(String.valueOf(mapLocations.get(position).getLat())));
        holder.mapDescription.setText(mapLocations.get(position).getLocationDescription());
        //holder.imageView.setText(mapLocations.get(position).getimage());
    }


    @Override
    public int getItemCount() {
        return mapLocations.size();
    }


}
