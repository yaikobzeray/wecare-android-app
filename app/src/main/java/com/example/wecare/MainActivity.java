package com.example.wecare;

import static com.example.wecare.R.id.subscribedList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.FrameLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements EditDialogBox.EditDialogListener, CameraAndGalleryChooser.CameraAndGalleryListener {

    private DatabaseReference user = FirebaseDatabase.getInstance().getReference("users").child("JpuVHMbkzFcX67MFmUkRukX9sSd2");
    UserProfileFragment userProfileFragment;
    private boolean nightMode = false;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

      SharedPreferences sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE);

       nightMode = sharedPreferences.getBoolean("night",false);

//        if(nightMode){
//            AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_YES);
//
//        }
//       SubscribedNgoListFragment companyList = new SubscribedNgoListFragment();
        userProfileFragment = new UserProfileFragment();
       FragmentManager fragmentManager = getSupportFragmentManager();
       FragmentTransaction transaction = fragmentManager.beginTransaction();
       transaction.add(R.id.mainActivity, userProfileFragment);
       transaction.commit();

   }


    @Override
    public void applyText(String text, String inputType) {
        if (!text.isEmpty()) {
            if (inputType.equals("Edit Name")) {
                user.child("name").setValue(text);
            }
            if (inputType.equals("Change Password")) {
//                pass.setText(text);
            }
        }
        if (inputType.equals("Edit Bio")) {
            user.child("bio").setValue(text);
        }
    }

    @Override
    public void getPhoto(Bitmap bitmapCamera, Uri uriGallery, int requestCode) {
       userProfileFragment.setImage(bitmapCamera,uriGallery,requestCode);

    }
}

