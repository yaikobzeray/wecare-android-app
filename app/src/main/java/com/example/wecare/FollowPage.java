package com.example.wecare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.Map;

public class FollowPage extends AppCompatActivity {
    private DatabaseReference companyProfile;
    private String fullName;
    private String bio;
    private boolean isFollowing = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followpage);

        // Initialize views
        ImageButton backButton = findViewById(R.id.backbtn);
        Button followButton = findViewById(R.id.followbtn);
        TextView name = findViewById(R.id.companyName);
        TextView bioView = findViewById(R.id.bio);
        ImageView profileImage = findViewById(R.id.profileImage);

        // Set click listeners
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FollowPage.this, MainActivity.class);
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

        // Firebase Database reference

        String currentuserId = "JpuVHMbkzFcX67MFmUkRukX9sSd2";
        companyProfile = FirebaseDatabase.getInstance().getReference().child("users").child(currentuserId);

        // Read data from Firebase
        companyProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Retrieve the user data as a Map
                    Map<String, Object> userData = (Map<String, Object>) snapshot.getValue();

                    // Extract user data
                    fullName = userData.get("name").toString();
                    bio = userData.get("bio").toString();
                    String profileImageUrl = userData.get("profileImage").toString();

                    // Display user data
                    Picasso.get().load(profileImageUrl).into(profileImage);
                    name.setText(fullName);
                    bioView.setText(bio);
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
