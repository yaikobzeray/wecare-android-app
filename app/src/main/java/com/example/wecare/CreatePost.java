package com.example.wecare;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

public class CreatePost extends AppCompatActivity implements  CameraAndGalleryChooser.CameraAndGalleryListener {

    ImageView imageView;
    Button selectPhotoBtn, postBtn;

    EditText titleTxt, descriptionTxt;

    String title;
    String description;

    Map<String,Object> postJson;

    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        imageView = findViewById(R.id.imageView);
        selectPhotoBtn = findViewById(R.id.button3);
        titleTxt = findViewById(R.id.editTextText);
        descriptionTxt = findViewById(R.id.editTextTextMultiLine);
        postJson = new HashMap<>();
        postBtn = findViewById(R.id.button4);


        description = descriptionTxt.getText().toString();

        selectPhotoBtn.setOnClickListener(view -> {
            CameraAndGalleryChooser cameraAndGalleryChooser = new CameraAndGalleryChooser();
            cameraAndGalleryChooser.show(getSupportFragmentManager(),"choose");
        });

        postBtn.setOnClickListener(view -> {
            postJson.put("description",descriptionTxt.getText());
            postJson.put("title",titleTxt.getText());
            System.out.println(postJson);

        });
    }

    @Override
    public void getPhoto(Bitmap bitmapCamera, Uri uriGallery, int requestCode) {
        switch (requestCode) {

            case 0:
                imageView.setImageBitmap(bitmapCamera);
                imageBitmap = bitmapCamera;

                break;
            case 1:
                imageView.setImageURI(uriGallery);

                break;
        }
    }
}