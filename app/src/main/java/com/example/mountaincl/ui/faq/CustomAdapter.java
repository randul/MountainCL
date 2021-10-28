package com.example.mountaincl.ui.faq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mountaincl.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private Context context;
    private List<Questions> qList;

    public CustomAdapter(Context ctx, List<Questions> list){
        context = ctx;
        qList = list;
    }

    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            this.textTitle = view.findViewById(R.id.textTitle);
            linearLayout = view.findViewById(R.id.relativeLayout);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the Layout(Instantiates list_item.xml
        // layout file into View object)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_questions, parent, false);

        // Passing view to ViewHolder
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, final int position) {
        holder.textTitle.setText(qList.get(position).getQtitle());
    }

    @Override
    public int getItemCount() {
        // Returns number of items
        // currently available in Adapter
        return qList.size();
    }



}
