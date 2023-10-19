package com.example.wecare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.Map;

public class ProfileDescs extends AppCompatActivity {
    private DatabaseReference CompanyProfile;
    private String fullName;
    private String email;
    //    private String Bio;
    private boolean isFollowing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_descs);

        // Initialize views
//        ImageView profileImg = findViewById(R.id.profileImage);
        TextView name = findViewById(R.id.CompanyName);
//        TextView bio = findViewById(R.id.Bio);
        TextView emailAD = findViewById(R.id.emails);
        Button followButton = findViewById(R.id.followbtn);
        ImageButton backButton = findViewById(R.id.backbtn);

        // Set click listeners
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileDescs.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtonText(followButton);
            }
        });

        CompanyProfile = FirebaseDatabase.getInstance().getReference().child("companies").child("6h2jOQowsyYCeWbyAFClpLoSgzj1");

        CompanyProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Retrieve the list of companies as a Map
                    Map<String, Object> companiesMap = (Map<String, Object>) snapshot.getValue();

                    // Iterate over the map and extract user data
                    fullName = companiesMap.get("name").toString();
                    email= companiesMap.get("email").toString();
//                    Bio = companiesMap.get("bio").toString();
//                    String image = companiesMap.get("profileImage").toString();
//                    Picasso.get().load(image).into(profileImg);
                    name.setText(fullName);
//                    bio.setText(Bio);
                    emailAD.setText(email);
                    Log.d("Firebase", "User data loaded");
                } else {
                    // Handle the case when the data doesn't exist
                    Log.d("Firebase", "No user data found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Database error: " + error.getMessage());
            }
        });
    }

    private void updateButtonText(Button followButton) {
        if (isFollowing) {
            followButton.setText("Follow");
            isFollowing = false;
        } else {
            followButton.setText("Unfollow");
            isFollowing = true;
        }
    }
}