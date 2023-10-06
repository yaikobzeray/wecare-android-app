package com.example.wecare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SubscribedNgoListActivity extends AppCompatActivity {

     RecyclerView recyclerView;
     MyAdapter myAdapter;
     ArrayList<Company> subscribedList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribed_ngo_list);

        subscribedList = new ArrayList<>();
        subscribedList.add(new Company("UNICEF","1,200,000",true,true));
        subscribedList.add(new Company("Muday","1,3980,000",false,true));
        subscribedList.add(new Company("Mekedoniya","10,000",false,true));
        subscribedList.add(new Company("Felegetsidk","500,0000",true,true));


        recyclerView = findViewById(R.id.followingRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



        myAdapter = new MyAdapter(this,subscribedList);

        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }
}