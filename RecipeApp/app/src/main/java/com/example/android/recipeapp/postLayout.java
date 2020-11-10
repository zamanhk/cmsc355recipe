package com.example.android.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class postLayout extends AppCompatActivity {

    private Button ingredientsButton, instructionButton, nutButton;
    private EditText captionBox, recipeNameBox;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_layout);

        ingredientsButton = findViewById(R.id.IngredientsView);
        instructionButton = findViewById(R.id.InstructionView);
        nutButton = findViewById(R.id.NutView);

        ingredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //create the intent to go to the ingredients view page?
            }
        });

        instructionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //create the intent to go to the instruction view page?
            }
        });

        nutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(postLayout.this, NutritionPageDisplay.class);
                startActivity(myIntent);
            }
        });


    }

}