package com.example.mountaincl.ui.logout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.mountaincl.R;
import com.example.mountaincl.helper.PrefManager;

public class LogOut extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.image_dialog, null);

        builder.setView(view).setTitle("Are you sure you want to log out?")
                .setPositiveButton(R.string.finishSession, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // when the user clicks on the camera button

                        logout();
                    }
                })
                .setNegativeButton(R.string.cancelFinish, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void logout(){
        //This calls the method logout from PrefManager
        PrefManager.getInstance(getContext()).logout();
        // this gets the current activity.
        Activity currentActivity = getActivity();
        // this finish() method ends the current activity.
        currentActivity.finish();
    }
}
