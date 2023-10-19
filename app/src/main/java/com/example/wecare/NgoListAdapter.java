package com.example.wecare;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NgoListAdapter extends RecyclerView.Adapter<NgoListAdapter.MyViewHolder> {

    Context context;

    ArrayList<SubscribedNgoListFragment.fetchCompany> followingList;

    public NgoListAdapter(Context context, ArrayList<SubscribedNgoListFragment.fetchCompany> followingList) {
        this.context = context;
        this.followingList = followingList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SubscribedNgoListFragment.fetchCompany company = followingList.get(position);
        holder.companyName.setText(company.name);
        System.out.println(company.getImgUrl());
        Picasso.get().load(company.getImgUrl()).into(holder.profilePic);
        holder.subscribers.setText(company.subscribers + " followers");
        if(!company.isVerified){
            holder.verified.setVisibility(View.GONE);
        }

        holder.unFollow.setOnClickListener(view -> {
            followingList.remove(position);
            notifyDataSetChanged();
        });

        holder.cdView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProfileDescs.class);
            // Pass any data you need to the ProfileDescs activity using intent extras
            intent.putExtra("companyName", company.name);
            intent.putExtra("imageUrl", company.getImgUrl());
            intent.putExtra("subscribers", company.subscribers);
            intent.putExtra("isVerified", company.isVerified);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return followingList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView companyName, subscribers;
        ImageView verified;

        ImageView profilePic;
        Button unFollow;
        ConstraintLayout emptyLayout;
        CardView cdView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            companyName = itemView.findViewById(R.id.companyName);
            profilePic = itemView.findViewById(R.id.profile_image);
            subscribers = itemView.findViewById(R.id.subscribersNo);
            verified = itemView.findViewById(R.id.verified);
            unFollow = itemView.findViewById(R.id.unfollowBtn);
            cdView = itemView.findViewById(R.id.subButton);



        }
    }
}