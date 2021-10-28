package com.example.mountaincl.ui.maps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.mountaincl.R;

public class MapDialog extends AppCompatDialogFragment {
    EditText editTextlat, editTextlon, editTextTitle, editTextDescription;
    ImageView imageView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        ProgressBar progressBar;
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //builder.setCancelable(false);


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_map, null);

        editTextTitle = view.findViewById(R.id.textViewAdapterTitle);
        editTextlat = view.findViewById(R.id.textViewAdapterLocationLt);
        editTextlon = view.findViewById(R.id.textViewAdapterLocationLn);
        editTextDescription = view.findViewById(R.id.textViewAdapterDescription);

        imageView = view.findViewById(R.id.imageViewAdapter);

        // Create the AlertDialog object and return it
        return builder.create();

    }



}
