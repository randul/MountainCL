package com.example.mountaincl.ui.equipment;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mountaincl.R;


public class EquipmentAdapter extends RecyclerView.Adapter <EquipmentAdapter.ViewHolder>{

    private Context context;
    private Equipment [] listData;

    public EquipmentAdapter(Context ctx, Equipment[] dataset){
        context = ctx;
        listData=dataset;
    }

    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView images;
        public TextView textTitle, textDescription;
        public LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            this.images = view.findViewById(R.id.imageViewAdapter);
            this.textTitle = view.findViewById(R.id.textTitle);
            this.textDescription = view.findViewById(R.id.textDescription);
            linearLayout = view.findViewById(R.id.EqLinearLayout);
        }
    }


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the Layout(Instantiates list_item.xml
        // layout file into View object)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_equipment, parent, false);

        // Passing view to ViewHolder
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    // Binding data to the object into specified position
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Equipment mylistData = listData [position];
        // TypeCast the data into the items
        holder.textTitle.setText(String.valueOf(listData[position].getName()));
        //holder.textTitle.setText(listData[position].getName());
        holder.textDescription.setText(String.valueOf(listData[position].getDescription()));
        holder.images.setImageResource(listData[position].getImage());

        //items are clickable and some information is displayed when users click on them
//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // display a toast with person name on item click
//                Toast.makeText(context,"click on item: "+mylistData.getDescription(), Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    @Override
    public int getItemCount() {
        // Returns number of items
        // currently available in Adapter
        return listData.length;
    }



}
