package com.example.mountaincl.ui.equipment;

import android.content.Context;
import android.widget.ImageView;
//import com.squareup.picasso.Picasso;

import com.example.mountaincl.R;

public class PicassoClient {

    public static void downloadImage(Context c, String imageUrl, ImageView img)
    {
        if(imageUrl.length()>0 && imageUrl!=null)
        {
           // Picasso.with(c).load(imageUrl).placeholder(R.drawable.placeholder).into(img);
        }else {
           // Picasso.with(c).load(R.drawable.placeholder).into(img);
        }
    }
}
