package com.example.mountaincl.ui.imageEditor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.mountaincl.R;

import java.time.LocalDateTime;

public class ImageSource extends AppCompatDialogFragment {

    private ImageView imageView;

    private static final int CAMERA_PERMISSION_CODE = 100;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";

    private static final int PICK_IMAGE = 100;
    private Uri fileUri;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.image_dialog, null);

       //imageView = view.findViewById(R.id.imageViewDialog);

        builder.setView(view).setTitle("Select Image Source")
                .setPositiveButton(R.string.camera, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // when the user clicks on the camera button

                    }
                })
                .setNegativeButton(R.string.gallery, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog

                    }
                });

        // Inizialize the image I want to pass


        // Create the AlertDialog object and return it
        return builder.create();
    }

    public interface ImageSourceListener{
        void applyImage();
    }

    private void openCamera(){
        // Here we make create a name for the new image,
        //the name will be the local date + local time
        String fileName;
        LocalDateTime myObj = LocalDateTime.now();
        fileName = String.valueOf(myObj);
        //Now we can create a intent with these values
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, fileName);
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        imageView.setImageURI(fileUri);
        startActivityForResult(intent, 1231);
    }

    private void openGallery(){

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == PICK_IMAGE) {
            Activity currentActivity = getActivity();
            imageView = currentActivity.findViewById(R.id.imageViewEdit);
            fileUri = intent.getData();
            imageView.setImageURI(fileUri);

        }

    }


}
