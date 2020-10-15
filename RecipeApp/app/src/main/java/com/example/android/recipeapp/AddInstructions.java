package com.example.android.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

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