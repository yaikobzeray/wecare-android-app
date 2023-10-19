package com.example.wecare;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Map;


public class UserProfileFragment extends Fragment {

    private LinearLayout nameLayout;
    private LinearLayout passwordLayout;
    private LinearLayout bioLayout;
    private TextView name;
    private TextView pass;
    private TextView bio;
    private ImageButton editPhoto;
    private ImageView profileImg;
    private StorageReference storageReference;
    private String fullName;
    private String bioString;
    private DatabaseReference user = FirebaseDatabase.getInstance().getReference("users").child("JpuVHMbkzFcX67MFmUkRukX9sSd2");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();

    private Switch switchDarkMode;

    private boolean isChecked = false;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean nightMode = false;

    private Button logoutBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        nameLayout = view.findViewById(R.id.nameLayout);
        passwordLayout = view.findViewById(R.id.linearLayout2);
        bioLayout = view.findViewById(R.id.linearLayout);
        name = view.findViewById(R.id.textView2);
        pass = view.findViewById(R.id.passTxt);
        bio = view.findViewById(R.id.bioTxt);
        editPhoto = view.findViewById(R.id.editIcon);
        profileImg = view.findViewById(R.id.profile_image);
        storageReference = FirebaseStorage.getInstance().getReference();
        switchDarkMode = view.findViewById(R.id.NightModeSwitch);
        logoutBtn = view.findViewById(R.id.logout);
        getUserInfo();

//        shared preference for dark mode
        sharedPreferences = getActivity().getSharedPreferences("Mode", Context.MODE_PRIVATE);


//        if(nightMode){
//            switchDarkMode.setChecked(nightMode);
//
//        }

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                log out functions

            }
        });

        nameLayout.setOnClickListener(v -> {
            EditDialogBox editName = new EditDialogBox("Edit Name", "Enter Your Name");
            editName.show(getChildFragmentManager(), "Edit Name");
        });

        passwordLayout.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "This feature will be implemented in the next update", Toast.LENGTH_SHORT).show();
        });

        bioLayout.setOnClickListener(v -> {
            EditDialogBox editBio = new EditDialogBox("Edit Bio", "Write Your Bio");
            editBio.show(getChildFragmentManager(), "Edit Bio");
        });

        editPhoto.setOnClickListener(v -> {
            CameraAndGalleryChooser cameraAndGalleryChooser = new CameraAndGalleryChooser();
            cameraAndGalleryChooser.show(getChildFragmentManager(), "choose");
        });

        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (nightMode) {
//                    AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_NO);
//                    editor = sharedPreferences.edit();
//                    editor.putBoolean("night",b);
//                }else {
//                    AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_YES);
//                    editor = sharedPreferences.edit();
//                    editor.putBoolean("night",b);
//                    switchDarkMode.setChecked(false);
//
//                    System.out.println(nightMode);
//                }
//                editor.apply();
            }
        });

        return view;
    }

    private void uploadProfilePhotoToFirebase(Uri uri) {
        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri)
                .addOnSuccessListener(taskSnapshot -> fileRef.getDownloadUrl()
                        .addOnSuccessListener(uriResult -> {
                            String profileImage = "profileImage";
                            user.child(profileImage).setValue(uriResult.toString());
                            Toast.makeText(requireContext(), "Profile changed", Toast.LENGTH_SHORT).show();
                        }))
                .addOnProgressListener(snapshot -> {
                    // Show upload progress if needed
                })
                .addOnFailureListener(e -> {
                    // Handle upload failure if needed
                });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = requireContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void getUserInfo() {
        DatabaseReference followedCompanies = FirebaseDatabase.getInstance().getReference().child("users").child("JpuVHMbkzFcX67MFmUkRukX9sSd2");
        followedCompanies.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Map<String, Object> usersMap = (Map<String, Object>) snapshot.getValue();
                    fullName = usersMap.get("name").toString();
                    bioString = usersMap.get("bio").toString();
                    String image = usersMap.get("profileImage").toString();
                    Picasso.get().load(image).into(profileImg);
                    name.setText(fullName);
                    bio.setText(bioString);
                    Log.d("Firebase", fullName);
                } else {
                    Log.d("Firebase", "No users found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error retrieving data: " + error.getMessage());
            }
        });
    }

    void setImage(Bitmap bitmapCamera, Uri uriGallery, int requestCode){
        switch (requestCode) {
            case 0:
                profileImg.setImageBitmap(bitmapCamera);
                break;
            case 1:
                uploadProfilePhotoToFirebase(uriGallery);
                break;
        }

    }
}

 class Upload {
    private String mName;
    private String mImageUrl;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String name, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

     public void setImageView(ImageView imageView){}
}
     class Profile{
    String fullName;
    String password;
    String bio;

         public Profile(String fullName, String password, String bio) {
             this.fullName = fullName;
             this.password = password;
             this.bio = bio;
         }

         public String getFullName() {
             return fullName;
         }

         public void setFullName(String fullName) {
             this.fullName = fullName;
         }

         public String getPassword() {
             return password;
         }

         public void setPassword(String password) {
             this.password = password;
         }

         public String getBio() {
             return bio;
         }

         public void setBio(String bio) {
             this.bio = bio;
         }


     }


