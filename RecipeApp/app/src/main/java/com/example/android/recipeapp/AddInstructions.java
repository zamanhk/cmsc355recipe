package com.example.android.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instructions);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button backButton = findViewById(R.id.Backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent backToPost = new Intent(AddInstructions.this, NewPost.class);
                startActivity(backToPost);
            }
        });
        Button saveInstructions = findViewById(R.id.saveButton);
        saveInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView instructionsView = findViewById(R.id.instructionsBox);
                String instructions = instructionsView.getText().toString();

                Intent intent = new Intent(AddInstructions.this, NewPost.class);
                intent.putExtra("instructions", instructions);
                startActivity(intent);

            }
        });

    }
}