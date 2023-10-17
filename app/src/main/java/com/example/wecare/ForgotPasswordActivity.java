package com.example.wecare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailText;
    private Button resetButton;

    private ImageView backButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);

        mAuth = FirebaseAuth.getInstance();

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        });








        emailText = findViewById(R.id.forgetpasswordemail);
        resetButton = findViewById(R.id.resetbutton);

        resetButton.setOnClickListener(view -> {
            // Get email from the EditText
            String email = emailText.getText().toString().trim();

            // Check if email is empty
            if (TextUtils.isEmpty(email)) {
                emailText.setError("Enter your email");
                return;
            }

            // Send password reset email
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotPasswordActivity.this, "Sent successfully.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ForgotPasswordActivity.this, "Send Failed!.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
}