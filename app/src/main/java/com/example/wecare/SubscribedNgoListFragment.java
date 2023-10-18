package com.example.wecare;

import static java.lang.Boolean.parseBoolean;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubscribedNgoListFragment extends Fragment {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<fetchCompany> subscribedList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_subscribed_ngo_list, container, false);

        subscribedList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.followingRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        getCompanyList();

        return view;
    }

    private void getCompanyList() {
        DatabaseReference followedCompanies = FirebaseDatabase.getInstance().getReference().child("companies");

        followedCompanies.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Company> companyList = new ArrayList<>();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    Company company = userSnapshot.getValue(Company.class);
                    companyList.add(company);
                }

                // Do something with the user list
                for (Company company : companyList) {
                    subscribedList.add(new fetchCompany(
                            company.getName(),
                            company.getImgUrl(),
                            "12000",
                            company.isVerified()
                    ));
                }

                myAdapter = new MyAdapter(requireContext(), subscribedList);

                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error if the retrieval is canceled
                Log.e("Firebase", "Error retrieving data: " + databaseError.getMessage());
            }
        });
    }

    class fetchCompany {
        String name;
        String imgUrl;
        String subscribers;
        Boolean isVerified;

        public fetchCompany(String name, String imgUrl, String subscribers, Boolean isVerified) {
            this.name = name;
            this.imgUrl = imgUrl;
            this.subscribers = subscribers;
            this.isVerified = isVerified;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSubscribers() {
            return subscribers;
        }

        public void setSubscribers(String subscribers) {
            this.subscribers = subscribers;
        }

        public Boolean getVerified() {
            return isVerified;
        }

        public void setVerified(Boolean verified) {
            isVerified = verified;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}