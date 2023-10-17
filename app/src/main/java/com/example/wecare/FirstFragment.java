package com.example.wecare;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Post> postArrayList = new ArrayList<>();
    private RecyclerView recyclerview;

    DatabaseReference database;

    MyAdapter myAdapter;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        dataInitialize();
        recyclerview = view.findViewById(R.id.recyclerview);
        database = FirebaseDatabase.getInstance().getReference("posts");
        DatabaseReference getImage = database.child("imgUrl");
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager( new LinearLayoutManager(getContext()));

         myAdapter = new MyAdapter(getContext(),postArrayList);
        recyclerview.setAdapter(myAdapter);



        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postArrayList.clear();


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Post post = new Post();

                    // Assuming your Post class has setters for title, description, imageUrl, and timeOfPost
                    post.setTitle(dataSnapshot.child("title").getValue(String.class));
                    post.setDescription(dataSnapshot.child("description").getValue(String.class));

                    // Get the image URL from the "imageUrl" child of each post
                    String imageUrl = dataSnapshot.child("imgUrl").getValue(String.class);
                    post.setImageUrl(imageUrl);

                    // Load the image into the ImageView using Picasso
                    ImageView imageView = new ImageView(requireContext());
                    Picasso.get().load(imageUrl).into(imageView);

                    post.setImageView(imageView);

                    if (post != null) {
                        postArrayList.add(post);
                    }
                }

                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

//    private void dataInitialize() {
//        postArrayList = new ArrayList<>();
//        postArrayList.add(new Post("https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Spaghetti_Bolognese_mit_Parmesan_oder_Grana_Padano.jpg/800px-Spaghetti_Bolognese_mit_Parmesan_oder_Grana_Padano.jpg", "2023-10-07 12:30", "Sample Title 1", "This is a sample description."));
//        postArrayList.add(new Post("https://cdn.pixabay.com/photo/2014/10/23/18/05/burger-500054_1280.jpg", "2023-10-08 09:45", "Sample Title 2", "Another sample description."));
//        postArrayList.add(new Post("https://cdn.pixabay.com/photo/2018/03/31/19/29/schnitzel-3279045_1280.jpg", "2023-10-09 18:20", "Sample Title 3", "Yet another sample description."));
//
//    }
}