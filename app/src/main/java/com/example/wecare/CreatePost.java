package com.example.wecare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class CreatePost extends AppCompatActivity implements  CameraAndGalleryChooser.CameraAndGalleryListener {

    ImageView imageView;
    Button selectPhotoBtn, postBtn;

    EditText titleTxt, descriptionTxt;

    String title;
    String description;

    Map<String,Object> postJson;

    Bitmap imageBitmap;
    Uri imageUri;

    ProgressBar progressBar;

    ImageButton backButton;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("posts");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        backButton = findViewById(R.id.button2);
        imageView = findViewById(R.id.imageView);
        selectPhotoBtn = findViewById(R.id.button3);
        titleTxt = findViewById(R.id.editTextText);
        descriptionTxt = findViewById(R.id.editTextTextMultiLine);
        postJson = new HashMap<>();
        postBtn = findViewById(R.id.button4);

        progressBar = findViewById(R.id.uploadProgress);



        progressBar.setVisibility(View.INVISIBLE);

        description = descriptionTxt.getText().toString();

        selectPhotoBtn.setOnClickListener(view -> {
            CameraAndGalleryChooser cameraAndGalleryChooser = new CameraAndGalleryChooser();
            cameraAndGalleryChooser.show(getSupportFragmentManager(),"choose");
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        postBtn.setOnClickListener(view -> {


            if(imageUri != null){
                uploadTofirebase(imageUri);
                finish();
            }else{
                Toast.makeText(this, "please select image", Toast.LENGTH_SHORT).show();
            }


        });
    }



    @Override
    public void getPhoto(Bitmap bitmapCamera, Uri uriGallery, int requestCode) {
        switch (requestCode) {

            case 0:
                imageView.setImageBitmap(bitmapCamera);
                imageBitmap = bitmapCamera;

                break;
            case 1:
                imageView.setImageURI(uriGallery);
                imageUri = uriGallery;
                break;
        }

    }

    public void uploadTofirebase(Uri uri){
        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExstension(uri));
        fileRef.putFile(uri).addOnSuccessListener(taskSnapshot -> {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Post post = new Post(uri.toString(),titleTxt.getText().toString(),descriptionTxt.getText().toString());
                            String postId = root.push().getKey();
                            root.child (postId). setValue(post);
                            titleTxt.setText(null);
                            descriptionTxt.setText(null);
                            imageView.setImageResource(R.drawable.person);
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(CreatePost.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                            postBtn.setVisibility(View.VISIBLE);

                        }
                    });
                })
                .addOnProgressListener(snapshot -> {
                    progressBar.setVisibility(View.VISIBLE);
                    postBtn.setVisibility(View.INVISIBLE);
                })
                .addOnFailureListener(e -> {
                    progressBar.setVisibility(View.INVISIBLE);
                    postBtn.setVisibility(View.VISIBLE);

                    Toast.makeText(this, "unable to upload", Toast.LENGTH_SHORT).show();
                });

    }

    private String getFileExstension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
}



