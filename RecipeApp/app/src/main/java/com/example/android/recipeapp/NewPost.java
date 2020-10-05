package com.example.android.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*******************************************************************************************
 * The new post page when button is clicked on main screen.
 *****************************************NOTES*********************************************
 * Need to transfer information into profile page
 * Link to post button on main screen
 *
 *******************************************************************************************/

public class NewPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);


        /*******************************************************************************************
         * Button to upload an image (includes camera/gallery option)
         *******************************************************************************************/

        //Button imageButton = findViewById(R.id.imageButton);

        /*******************************************************************************************
         * Button to navigate to add ingredients page
         *******************************************************************************************/

        Button addIngredientsButton = findViewById(R.id.ingredientsButton);
        addIngredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingredientsIntent = new Intent(getApplicationContext(), AddIngredients.class);
                startActivity(ingredientsIntent);
            }
        });

        /*******************************************************************************************
         * Button to navigate to add instructions page
         *******************************************************************************************/

        Button addInstructionsButton = findViewById(R.id.instructionsButton);
        addInstructionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instructionsIntent = new Intent(getApplicationContext(), AddInstructions.class);
                startActivity(instructionsIntent);
            }
        });

        /*******************************************************************************************
         * Button to navigate to add nutrition facts page
         *******************************************************************************************/
        Button addNutritionFacts = findViewById(R.id.nutritionButton);
        addNutritionFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nutritionIntent = new Intent(getApplicationContext(), NutritionPageDisplay.class);
                startActivity(nutritionIntent);
            }
        });

        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //post onto profile??
            }
        });

    }
}