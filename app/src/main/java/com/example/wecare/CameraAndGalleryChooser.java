package com.example.wecare;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.content.Intent;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class CameraAndGalleryChooser extends AppCompatDialogFragment{

    public CameraAndGalleryListener cameraAndGalleryListener;
    private LinearLayout cameraLayout;
    private LinearLayout galleryLayout;
    private int requestCodeApplied;

    Bitmap cameraBitmap;
    Uri galleryImageUri;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view =  layoutInflater.inflate(R.layout.fragment_camera_and_gallery_chooser,null);
        cameraLayout = view.findViewById(R.id.camera);
        galleryLayout = view.findViewById(R.id.gallery);


        cameraLayout.setOnClickListener(view1 -> {
            System.out.println("camera");
//            To take picture from camera

            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePicture, 0);//zero can be replaced with any action code



        } );


        galleryLayout.setOnClickListener(view1 -> {
            System.out.println("gallery");
            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

        } );

        builder.setView(view);

        return builder.create();


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            cameraAndGalleryListener = (CameraAndGalleryListener) context;
        } catch (ClassCastException e) {
            System.out.println(e.toString());
        }
    }

    public interface CameraAndGalleryListener{
        void getPhoto(Bitmap bitmapCamera,Uri uriGallery,int requestCode);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {

            case 0:
                if (resultCode == RESULT_OK) {
                    Bundle extras = imageReturnedIntent.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    cameraAndGalleryListener.getPhoto(imageBitmap,null,0);
                    dismiss();

                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    cameraAndGalleryListener.getPhoto(null,selectedImage,1);
                    dismiss();

                }
                break;
        }
    }


}
