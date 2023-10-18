package com.example.wecare;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Patterns;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupPage extends AppCompatActivity {

    static  final Pattern Password_Pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
    private  String fullName;
    private   String email;

    private  String password;
    private   String confirmPassword;
    private Button signUpButton;
    private EditText editName;
    private EditText emailText;
    private   EditText passwordText;
    private  EditText confirmPass;
    private CheckBox  checkbox;
    private TextView loginText;

    private FirebaseAuth mAuth;

    private Map<String, String> formData = new HashMap<>();


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_signup_page);
        TextView textView = findViewById(R.id.conditionText);




        TextView asCompany = findViewById(R.id.signupTextcompany);
        SpannableString companySignupText  = new SpannableString((asCompany.getText()));
        ClickableSpan companysingup = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(SignupPage.this, sgnupascompany.class));
                finish(); // O
            }
        };
        companySignupText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 31, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        companySignupText.setSpan(companysingup,31, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        asCompany.setText(companySignupText);
        asCompany.setMovementMethod(LinkMovementMethod.getInstance());











        loginText = findViewById(R.id.loginText);
        // Make "Login" clickable
        SpannableString spannableString = new SpannableString(loginText.getText());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(SignupPage.this, LoginActivity.class));
                finish(); // O
            }



        };




        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 17, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(clickableSpan,17, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        loginText.setText(spannableString);
        loginText.setMovementMethod(LinkMovementMethod.getInstance());

        String text = "I have read terms and Conditions.";
        SpannableString ss =  new SpannableString(text);


        ForegroundColorSpan Fscs = new ForegroundColorSpan(Color.parseColor("#B02196F3"));
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);


        ss.setSpan(Fscs,12,16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(boldSpan,12,16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(Fscs,22,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(boldSpan,22,32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
//        This text manipulation ends here
//        The form validation starts here

        editName = findViewById(R.id.fullnameField);
        emailText = findViewById(R.id.emailField);
        passwordText = findViewById(R.id.passwordfield);
        confirmPass = findViewById(R.id.confirmpassfield);
        checkbox = findViewById(R.id.checkbox_condition);










        signUpButton = findViewById(R.id.signUpbutton);




//        where we signed up our user by accepting neccesary information

        checkbox.setOnCheckedChangeListener((compoundButton, b) -> {


            if(b){
                signUpButton.setOnClickListener(view ->{
                    fullName = editName.getText().toString();
                    email = emailText.getText().toString();
                    password = passwordText.getText().toString();
                    confirmPassword = confirmPass.getText().toString();





                    if (validateName(fullName) && validateEmail(email) && validatePassword(password) && validatConfirmation(password,confirmPassword)) {

//                        formData.put("name", fullName);
//                        formData.put("email",email);
//                        formData.put("password",password);

                        mAuth.createUserWithEmailAndPassword(email, password)
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
                                                String userName = fullName; // Get the user's name from your UI or wherever it's stored
                                                String profileImageUrl = ""; // Get the profile image URL
                                                String emailAdress= email;
                                                boolean isVerified = user.isEmailVerified();

                                                storeUserDetailsInDatabase(userID, userName,emailAdress ,profileImageUrl, isVerified);
                                            }
                                            updateUI(user);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(SignupPage.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                            updateUI(null);
                                        }
                                    }
                                });




                        Toast.makeText(this, "Checkbox is checked!", Toast.LENGTH_SHORT).show();
                    }


                });

            }else{
                Toast.makeText(this, "Checkbox is Unchecked!", Toast.LENGTH_SHORT).show();

            };
        });



    }

// this is a place where we store user information in to a reatime database
    private void storeUserDetailsInDatabase(String userID, String userName,String emailAdress, String profileImageUrl, boolean isVerified) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userID);
        databaseReference.child("name").setValue(userName);
        databaseReference.child("email").setValue(emailAdress);
        databaseReference.child("profileImage").setValue(profileImageUrl);
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
    }




//Below this comment all code snipets are validation
    private  boolean validateName(String fullName){
        // Validate the user's full name

        if(fullName.isEmpty()){
            editName.setError("This field can not be empty");
            return  false;

        }else{
            editName.setError(null);
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
    private  boolean validatePassword(String password){
        // Validate the user's password

        if (password.isEmpty()) {
            passwordText.setError("Insert a valid password");
            return false;
        } else if (!Password_Pattern.matcher(password).matches()) {
            passwordText.setError("Password is too weak. It must contain at least 1 uppercase letter, 1 lowercase letter, and 1 special character");
            return false;
        } else {
            passwordText.setError(null); // Clear error if password is valid
            return true;
        }
    }


    private boolean validatConfirmation(String password,String confirmPassword){
        // Validate the user's password confirmation

        if (!password.equals(confirmPassword)) {
            confirmPass.setError("Passwords don't match. Try again.");
            return false;
        } else {
            confirmPass.setError(null); // Clear error if passwords match
            return true;
        }
    }













}