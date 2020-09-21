package com.example.cookingrecipeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.StringReader;
import java.util.Objects;

public class NutritionPageDisplay extends AppCompatActivity {

    final String GRAMS = "g";
    final String MILIGRAMS = "mg";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_page_display);



        Intent intent = getIntent();
        String servingSizeString = intent.getStringExtra(MainActivity.SERVINGSIZE);
        String caloriesString = intent.getStringExtra(MainActivity.CALORIES) + GRAMS;
        String totalfatString = intent.getStringExtra(MainActivity.TOTALFAT) + GRAMS;
        String satfatString = intent.getStringExtra(MainActivity.SATFAT) + GRAMS;
        String cholesterolString = intent.getStringExtra(MainActivity.CHOLESTEROL) + MILIGRAMS;
        String sodiumString = intent.getStringExtra(MainActivity.SODIUM) + MILIGRAMS;
        String totalCarbString = intent.getStringExtra(MainActivity.TOTALCARBOHYDRATES) + GRAMS;
        String dietaryFiberString = intent.getStringExtra(MainActivity.DIETARYFIBER) + GRAMS;
        String proteinString = intent.getStringExtra(MainActivity.PROTEIN) + GRAMS;

        TextView servingSizeView = (TextView) findViewById(R.id.servingSizeView);
        TextView caloriesView = (TextView) findViewById(R.id.calorieView);
        TextView totalFatView = (TextView) findViewById(R.id.fatView);
        TextView satFatView = (TextView) findViewById(R.id.saturatedFatView);
        TextView cholesterolView = (TextView) findViewById(R.id.cholesterolView);
        TextView sodiumView = (TextView) findViewById(R.id.sodiumView);
        TextView totalCarbohydratesView = (TextView) findViewById(R.id.carbView);
        TextView dietaryFiberView = (TextView) findViewById(R.id.fiberView);
        TextView proteinView = (TextView) findViewById(R.id.proteinView);


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