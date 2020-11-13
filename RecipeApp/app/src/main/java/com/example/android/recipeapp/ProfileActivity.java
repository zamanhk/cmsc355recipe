package com.example.android.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity
{
    private TextView profileName, userName, bio;
    private CircleImageView userImage;

    private DatabaseReference userRef;
    private FirebaseAuth myAuth;

    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        profileName = (TextView) findViewById(R.id.myProfileUsername);
        userName = (TextView) findViewById(R.id.myUserUsername);
        bio = (TextView) findViewById(R.id.bio);
        userImage = (CircleImageView) findViewById(R.id.myProfileImage);

        myAuth = FirebaseAuth.getInstance();
        currentUserId = myAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    String userNameRetrieval = dataSnapshot.child("Username").getValue().toString();
                    String fullNameRetrieval = dataSnapshot.child("FullName").getValue().toString();
                    String bioRetrieval = dataSnapshot.child("Biography").getValue().toString();
                    String profileImageRetrieval = dataSnapshot.child("ProfileImage").getValue().toString();

                    Picasso.get().load(profileImageRetrieval).placeholder(R.drawable.profile).into(userImage);
                    profileName.setText("Full Name: " + fullNameRetrieval);
                    userName.setText("Username: " + userNameRetrieval);
                    bio.setText("Biography: " + bioRetrieval);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}