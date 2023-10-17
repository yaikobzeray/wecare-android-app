package com.example.wecare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.wecare.databinding.ActivityCompanyMainDashboardBinding;
import com.example.wecare.databinding.ActivityMainBinding;

public class CompanyMainDashboard extends AppCompatActivity {


    private ActivityCompanyMainDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompanyMainDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new FirstFragment());
        final int home = R.id.home;
        final int subscribe = R.id.subscribe;
        final int setting = R.id.setting;

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {










            if (item.getItemId() == home) {
                replaceFragment(new FirstFragment());
                return true;
            } else if (item.getItemId() == subscribe) {
                replaceFragment(new SecondFragment());
                return true;
            } else if (item.getItemId() == setting) {
                replaceFragment(new ThirdFragment());
                return true;
            } else {
                return false;
            }
        });


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
    }
