package com.example.android.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MyRecipesActivity extends AppCompatActivity {
    private TextView recipeName, ingredients, instructions, nutrition;
    private DatabaseReference userRef;
    private FirebaseAuth myAuth;

    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipies);

        recipeName = (TextView) findViewById(R.id.recipeNames);
        ingredients = (TextView) findViewById(R.id.IngredientsText);
        instructions = (TextView) findViewById(R.id.InstructionsText);
        nutrition = (TextView) findViewById(R.id.NutText);

        myAuth = FirebaseAuth.getInstance();
        currentUserId = myAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    String recipeNameRetrieval = dataSnapshot.child("recipes").getValue().toString();
//                    String descriptionRetrieval = dataSnapshot.child("recipes").child("Description").getValue().toString();
//                    String ingredientRetrieval = dataSnapshot.child("recipes").child("Ingredients").getValue().toString();
//                    String instructionRetrieval = dataSnapshot.child("recipes").child("Instructions").getValue().toString();
//                    String foodImageRetrieval = dataSnapshot.child("recipes").child("imageuri").getValue().toString();
//                    String nutrionSetRetrieval = dataSnapshot.child("recipes").child("Nutrition Facts").getValue().toString();

                    recipeName.setText(recipeNameRetrieval);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    }