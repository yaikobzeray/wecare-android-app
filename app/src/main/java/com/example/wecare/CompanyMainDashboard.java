package com.example.wecare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.example.wecare.databinding.ActivityCompanyMainDashboardBinding;
import com.example.wecare.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CompanyMainDashboard extends AppCompatActivity  implements  EditDialogBox.EditDialogListener, CameraAndGalleryChooser.CameraAndGalleryListener{


    private ActivityCompanyMainDashboardBinding binding;

    UserProfileFragment userProfileFragment;

    private DatabaseReference user = FirebaseDatabase.getInstance().getReference("users").child("JpuVHMbkzFcX67MFmUkRukX9sSd2");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompanyMainDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new FirstFragment());
        final int home = R.id.home;
        final int subscribe = R.id.subscribe;
        final int setting = R.id.setting;
        final int add = R.id.favourite;

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {










            if (item.getItemId() == home) {
                replaceFragment(new FirstFragment());
                return true;
            } else if (item.getItemId() == subscribe) {
                replaceFragment(new SubscribedNgoListFragment());
                return true;
            } else if (item.getItemId() == setting) {
                replaceFragment(new UserProfileFragment());
                return true;
            } else if (item.getItemId() == add) {

                Intent intent = new Intent(getApplicationContext(), CreatePost.class);
                startActivity(intent);
            } else {
                return false;
            }
            return  false;
        });


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void getPhoto(Bitmap bitmapCamera, Uri uriGallery, int requestCode) {
        userProfileFragment.setImage(bitmapCamera,uriGallery,requestCode);
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
}
