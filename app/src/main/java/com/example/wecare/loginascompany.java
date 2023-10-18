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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginascompany extends AppCompatActivity {
private TextView signUpText;
private Button loginButton;
private EditText emailText;
private EditText passwordText;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginascompany);
        mAuth  = FirebaseAuth.getInstance();




        signUpText = findViewById(R.id.loginCompanyText);
        // Make "Login" clickable
        SpannableString spannableString = new SpannableString(signUpText.getText());




        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(loginascompany.this, sgnupascompany.class));
                finish(); // O
            }



        };

        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 23, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(clickableSpan,23, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUpText.setText(spannableString);
        signUpText.setMovementMethod(LinkMovementMethod.getInstance());








        emailText = findViewById(R.id.loginAsCompanyEmail);
        passwordText = findViewById(R.id.loginAsCompanyPassword);
        loginButton =  findViewById(R.id.loginAsCompanyButton);



        loginButton.setOnClickListener(view -> {

           String  email = emailText.getText().toString();
           String  password = passwordText.getText().toString();
            if ( validateEmail(email) && validatePassword(password) ) {

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                    Intent intent = new Intent(loginascompany.this, CompanyMainDashboard.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(loginascompany.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });





            }





        });








    }


    //    where  we check if our loggedin or not
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

    //    This is validation
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


    //    This Is Validatuion
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
}