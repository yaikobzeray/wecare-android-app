package com.example.wecare;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static com.example.wecare.SignupPage.Password_Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wecare.databinding.ActivityLoginascompanyBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sgnupascompany extends AppCompatActivity {
    private Button signUpButton;
    private EditText password;
    private EditText emailText;
    private EditText companyName;
    private FirebaseAuth mAuth;
    private TextView loginText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sgnupascompany);






        TextView asCompany = findViewById(R.id.asUser);
        SpannableString companySignupText  = new SpannableString((asCompany.getText()));
        ClickableSpan companysingup = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(sgnupascompany.this, SignupPage.class));
                finish(); // O
            }
        };
        companySignupText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 28, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        companySignupText.setSpan(companysingup,28, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        asCompany.setText(companySignupText);
        asCompany.setMovementMethod(LinkMovementMethod.getInstance());





















        loginText = findViewById(R.id.signUpText);
        // Make "Login" clickable
        SpannableString spannableString = new SpannableString(loginText.getText());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(sgnupascompany.this, loginascompany.class));
                finish(); // O
            }



        };




        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 17, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(clickableSpan,17, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        loginText.setText(spannableString);
        loginText.setMovementMethod(LinkMovementMethod.getInstance());


















        companyName = findViewById(R.id.companyName);
        emailText = findViewById(R.id.companyEmailAdress);
        password = findViewById(R.id.companyPassword);



        signUpButton = findViewById(R.id.companySigup);

        signUpButton.setOnClickListener(view -> {
            String name = companyName.getText().toString();
            String emailAdress = emailText.getText().toString();
           String  passwordText = password.getText().toString();






            if (validateName(name) && validateEmail(emailAdress) && validatePassword(passwordText) ) {


                mAuth.createUserWithEmailAndPassword(emailAdress, passwordText)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    // Store the user's name in the Realtime Database
                                    if (user != null) {
                                        String userID = user.getUid();
                                        String userName = name; // Get the user's name from your UI or wherever it's stored
                                        String profileImageUrl = "https://firebasestorage.googleapis.com/v0/b/wecare-80ca8.appspot.com/o/1696962130521.png?alt=media&token=a90b0c3d-c15e-4d60-af9d-9b92b04e77f31`"; // Get the profile image URL
                                        String emailAdres = emailAdress;
                                        boolean isVerified = user.isEmailVerified();

                                        storeUserDetailsInDatabase(userID, userName,emailAdress ,profileImageUrl, isVerified);
                                    }
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(sgnupascompany.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });




            }






        });

    }



    private  boolean validateName(String fullName){
        // Validate the user's full name

        if(fullName.isEmpty()){
            companyName.setError("This field can not be empty");
            return  false;

        }else{
            companyName.setError(null);
            return  true;
        }

    }

    private  boolean validateEmail(String email){
        // Validate the user's email adress

        if(email.isEmpty()){
            emailText.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Pleace enter a valid email");
            return false;

        }else{
            emailText.setError(null);
            return true;
        }




    }
    private  boolean validatePassword(String passwords){
        // Validate the user's password

        if (passwords.isEmpty()) {
            password.setError("Insert a valid password");
            return false;
        } else if (!Password_Pattern.matcher(passwords).matches()) {
            password.setError("Password is too weak. It must contain at least 1 uppercase letter, 1 lowercase letter, and 1 special character");
            return false;
        } else {
            password.setError(null); // Clear error if password is valid
            return true;
        }
    }


    private void storeUserDetailsInDatabase(String userID, String userName,String emailAdress, String profileImageUrl, boolean isVerified) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("companies").child(userID);

        databaseReference.child("name").setValue(userName);
        databaseReference.child("email").setValue(emailAdress);
        databaseReference.child("imgUrl").setValue(profileImageUrl);
        databaseReference.child("isVerified").setValue(isVerified);
    }



    //    where we check if we sindeup succesfully
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // User is signed in
            String displayName = user.getDisplayName();
            String email = user.getEmail();

            // You can perform actions like displaying the user's name or email in the UI
            // or navigate to a different screen indicating successful login.

            Toast.makeText(this, "Welcome, " + email, Toast.LENGTH_SHORT).show();
        } else {
            // User is signed out
            // You can update the UI to show a login form or any other relevant content.
        }


    }}