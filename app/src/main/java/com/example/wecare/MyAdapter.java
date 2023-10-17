package com.example.wecare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;

    public MyAdapter(Context context, ArrayList<Post> postArrayList) {
        this.context = context;
        this.postArrayList = postArrayList;
    }

    ArrayList<Post> postArrayList;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v  = LayoutInflater.from(context).inflate(R.layout.postitem,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.humaniterian) // Placeholder image
                .error(R.drawable.humaniterian); // E

        Post post = postArrayList.get(position);

        holder.title.setText(post.getTitle());
        holder.description.setText(post.getBody());
        holder.postTime.setText(post.getTimeOfPost());
        Picasso.get().load(post.getImageUrl()).into(holder.postImage);


//
//        Picasso.get().load(post.getImageUrl()).into(postImage);

    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView postImage;
            TextView  title;
            TextView description;
            TextView postTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            postImage = itemView.findViewById(R.id.postImage);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.body);
            postTime = itemView.findViewById(R.id.timeInfo);
        }
    }
}
