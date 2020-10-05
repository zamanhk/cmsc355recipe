package com.example.android.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NutritionPageDisplay extends AppCompatActivity {

    /*****************************
     * final Constants for grams and milligrams
     *
     ******************************/

    final String GRAMS = "g";
    final String MILIGRAMS = "mg";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_page_display);


        /**************************
         * Get the intent being sent to this activity
         * Initialize and declare strings to the numbers given by the user
         * Initialize the textview
         **************************/

        Intent intent = getIntent();
        String servingSizeString = intent.getStringExtra(NutritionEdit.SERVINGSIZE);
        String caloriesString = intent.getStringExtra(NutritionEdit.CALORIES) + GRAMS;
        String totalfatString = intent.getStringExtra(NutritionEdit.TOTALFAT) + GRAMS;
        String satfatString = intent.getStringExtra(NutritionEdit.SATFAT) + GRAMS;
        String cholesterolString = intent.getStringExtra(NutritionEdit.CHOLESTEROL) + MILIGRAMS;
        String sodiumString = intent.getStringExtra(NutritionEdit.SODIUM) + MILIGRAMS;
        String totalCarbString = intent.getStringExtra(NutritionEdit.TOTALCARBOHYDRATES) + GRAMS;
        String dietaryFiberString = intent.getStringExtra(NutritionEdit.DIETARYFIBER) + GRAMS;
        String proteinString = intent.getStringExtra(NutritionEdit.PROTEIN) + GRAMS;

        TextView servingSizeView = (TextView) findViewById(R.id.servingSizeView);
        TextView caloriesView = (TextView) findViewById(R.id.calorieView);
        TextView totalFatView = (TextView) findViewById(R.id.fatView);
        TextView satFatView = (TextView) findViewById(R.id.saturatedFatView);
        TextView cholesterolView = (TextView) findViewById(R.id.cholesterolView);
        TextView sodiumView = (TextView) findViewById(R.id.sodiumView);
        TextView totalCarbohydratesView = (TextView) findViewById(R.id.carbView);
        TextView dietaryFiberView = (TextView) findViewById(R.id.fiberView);
        TextView proteinView = (TextView) findViewById(R.id.proteinView);


        /*****************************
         * Set the TextView to display the String numbers
         *
         ******************************/


        servingSizeView.setText(servingSizeString);
        caloriesView.setText(caloriesString);
        totalFatView.setText(totalfatString);
        satFatView.setText(satfatString);
        cholesterolView.setText(cholesterolString);
        sodiumView.setText(sodiumString);
        totalCarbohydratesView.setText(totalCarbString);
        dietaryFiberView.setText(dietaryFiberString);
        proteinView.setText(proteinString);


    }


}