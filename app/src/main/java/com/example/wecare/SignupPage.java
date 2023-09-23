package com.example.wecare;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class SignupPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        TextView textView = findViewById(R.id.conditionText);
        String text = "I have read terms and Conditions.";
        SpannableString ss =  new SpannableString(text);


        ForegroundColorSpan Fscs = new ForegroundColorSpan(Color.BLUE);
        BackgroundColorSpan Bscs = new BackgroundColorSpan(Color.BLUE);

        ss.setSpan(Fscs,12,16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(Bscs,22,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);

    }
}