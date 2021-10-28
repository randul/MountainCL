package com.example.mountaincl.ui.imageEditor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mountaincl.R;

import java.time.LocalDateTime;

public class ImageEditor extends AppCompatActivity {

    ImageView imageView;
    ImageView navigationImage;

    private static final int PICK_IMAGE = 100;
    private Uri fileUri;

    final int PIC_CROP = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_webview);

        navigationImage = findViewById(R.id.imageMenuEquipment);
        //When we click on the navigationImage the activity closes and we go back to
        //the main activity
        navigationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





        WebView myWeb = (WebView) findViewById(R.id.webView);
        myWeb.setWebViewClient(new WebViewClient());
        myWeb.loadUrl("http://localhost/android/webpage/index.html");






//        imageView = (ImageView) findViewById(R.id.imageViewEdit);
//
//        selectSource = (Button) findViewById(R.id.selectImageButton);
//
//        cropButton = (ImageButton) findViewById(R.id.cropButton);
//
//        selectSource.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGallery();
//            }
//        });
//
//        cropButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //this function allows user to crop images
//                performCrop();
//            }
//        });

    }

    public void openDialog (){
        ImageSource imageSource = new ImageSource();
        imageSource.show(getSupportFragmentManager(), "Select Image Source");
        
        if(imageSource.equals(true)){
            openCamera();
        }else if(imageSource.equals(false)){
            openGallery();
        }
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
            fileUri = intent.getData();
            imageView.setImageURI(fileUri);
        }
        //user is returning from cropping the image
        else if(requestCode == PIC_CROP){
            //get the returned data
            Bundle extras = intent.getExtras();
            //get the cropped bitmap
            Bitmap myImage = extras.getParcelable("intent");
            //retrieve a reference to the ImageView
            ImageView picView = (ImageView)findViewById(R.id.imageViewEdit);

            picView.setImageBitmap(myImage);
        }
    }

    private void performCrop(){
        try {
            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(fileUri, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);

        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }


}