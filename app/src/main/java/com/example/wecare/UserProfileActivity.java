package com.example.wecare;


import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;





public class UserProfileActivity extends AppCompatActivity implements EditDialogBox.EditDialogListener , CameraAndGalleryChooser.CameraAndGalleryListener {

    LinearLayout nameLayout;
    LinearLayout passwordLayout;

    LinearLayout bioLayout;

    TextView name;

    TextView pass;

    TextView bio;

    ImageButton editPhoto;

    ImageView profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        nameLayout = findViewById(R.id.nameLayout);
        passwordLayout = findViewById(R.id.linearLayout2);
        bioLayout = findViewById(R.id.linearLayout);
        name = findViewById(R.id.textView2);
        pass = findViewById(R.id.passTxt);
        bio = findViewById(R.id.bioTxt);
        editPhoto = findViewById(R.id.editIcon);
        profileImg = findViewById(R.id.profile_image);




        nameLayout.setOnClickListener(view -> {
           EditDialogBox editName = new EditDialogBox("Edit Name","Enter Your Name");
           editName.show(getSupportFragmentManager(),"Edit Name");
        });

        passwordLayout.setOnClickListener(view ->{
            EditDialogBox editPass = new EditDialogBox("Change Password","Change Password");
            editPass.show(getSupportFragmentManager(),"Edit Pass");
        });

        bioLayout.setOnClickListener(view ->{
            EditDialogBox editBio = new EditDialogBox("Edit Bio","Write Your Bio");
            editBio.show(getSupportFragmentManager(),"Edit Bio");
        });

        editPhoto.setOnClickListener(view -> {

            CameraAndGalleryChooser cameraAndGalleryChooser = new CameraAndGalleryChooser();
            cameraAndGalleryChooser.show(getSupportFragmentManager(),"choose");




    });


    }



    @Override
    public void applyText(String text,String inputType) {

           if(!text.isEmpty()){
               if(inputType == "Edit Name"){
                   name.setText(text);
               }

               if(inputType == "Change Password"){
                   pass.setText(text);

               }

           }
        if(inputType == "Edit Bio"){
            bio.setText(text);

        }




    }


    @Override
    public void getPhoto(Bitmap bitmapCamera, Uri uriGallery, int requestCode) {
        switch (requestCode) {

            case 0:
                profileImg.setImageBitmap(bitmapCamera);

                break;
            case 1:
              profileImg.setImageURI(uriGallery);
                break;
        }
    }

    }
