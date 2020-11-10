package com.example.android.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NutritionEdit extends AppCompatActivity
{

    /*******************************************************************************************
     * Declare and Initialize static final variables
     * as keys to the next activity
     *******************************************************************************************/

    public static final String CALORIES = "com.example.android.recipeapp.CALORIES";
    public static final String TOTALFAT = "com.example.android.recipeapp.TOTALFAT";
    public static final String SATFAT = "com.example.android.recipeapp.SATFAT";
    public static final String CHOLESTEROL = "com.example.android.recipeapp.CHOLESTEROL";
    public static final String SODIUM = "com.example.android.recipeapp.SODIUM";
    public static final String TOTALCARBOHYDRATES = "com.example.android.recipeapp.TOTALCARBOHYDRATE";
    public static final String DIETARYFIBER = "com.example.android.recipeapp.DIETARYFIBER";
    public static final String PROTEIN = "com.example.android.recipeapp.PROTEIN";
    public static final String SERVINGSIZE = "com.example.android.recipeapp.SERVINGSIZE";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_edit);

        Button editBtn = findViewById(R.id.enterBtn);
        editBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                /*******************************************************************************************
                 * Grab the recipe name, description, image, ingredients, and instructions from
                 * AddInstructions.class
                 *******************************************************************************************/

                String recipeName = getIntent().getStringExtra("recipeName");
                String description = getIntent().getStringExtra("description");
                String image = getIntent().getStringExtra("image"); // This is the Uri String.  Remember to convert to Uri at the end
                String ingredients = getIntent().getStringExtra("ingredients");
                String instructions = getIntent().getStringExtra("instructions");

                /*******************************************************************************************
                 * Declare and Initialize EditText
                 * Set String variables to the number within the EditText
                 *******************************************************************************************/

                EditText servingSizeNum = findViewById(R.id.servingSizeNum);
                EditText calorieNum = findViewById(R.id.calNum);
                EditText fatNum = findViewById(R.id.fatNum);
                EditText satFatNum = findViewById(R.id.satFatNum);
                EditText cholesterolNum = findViewById(R.id.cholesterolNum);
                EditText sodiumNum = findViewById(R.id.sodiumNum);
                EditText totalCarbNum = findViewById(R.id.totalCarbNum);
                EditText fiberNum = findViewById(R.id.fiberNum);
                EditText proteinNum = findViewById(R.id.proteinNum);

                String servingSizeString = servingSizeNum.getText().toString();
                String calString = calorieNum.getText().toString();
                String fatString = fatNum.getText().toString();
                String satFatString =  satFatNum.getText().toString();
                String cholesterolString = cholesterolNum.getText().toString();
                String sodiumString = sodiumNum.getText().toString();
                String totalCarbString = totalCarbNum.getText().toString();
                String fiberString = fiberNum.getText().toString();
                String proteinString = proteinNum.getText().toString();

                /*******************************************************************************************
                 * Declare and Initialize the intent
                 * Send these keys and values to the Display page
                 *******************************************************************************************/

                Intent displayIntent = new Intent(getApplicationContext(), NutritionPageDisplay.class);
                displayIntent.putExtra(SERVINGSIZE, servingSizeString);
                displayIntent.putExtra(CALORIES, calString);
                displayIntent.putExtra(TOTALFAT, fatString);
                displayIntent.putExtra(SATFAT, satFatString);
                displayIntent.putExtra(CHOLESTEROL, cholesterolString);
                displayIntent.putExtra(SODIUM, sodiumString);
                displayIntent.putExtra(TOTALCARBOHYDRATES, totalCarbString);
                displayIntent.putExtra(DIETARYFIBER, fiberString);
                displayIntent.putExtra(PROTEIN, proteinString);

                SendToDisplayPage(recipeName, description, image, ingredients, instructions, displayIntent);
            }

            private void SendToDisplayPage(String recipeName, String description, String image, String ingredients, String instructions, Intent intent)
            {
                intent.putExtra("recipeName", recipeName);
                intent.putExtra("description", description);
                intent.putExtra("image", image);
                intent.putExtra("ingredients", ingredients);
                intent.putExtra("instructions", instructions);
                startActivity(intent);
            }
        });

    }
}