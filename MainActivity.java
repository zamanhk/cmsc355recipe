package com.example.cookingrecipeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    public static final String CALORIES = "com.example.cookingrecipeproject.CALORIES";
    public static final String TOTALFAT = "com.example.cookingrecipeproject.TOTALFAT";
    public static final String SATFAT = "com.example.cookingrecipeproject.SATFAT";
    public static final String CHOLESTEROL = "com.example.cookingrecipeproject.CHOLESTEROL";
    public static final String SODIUM = "com.example.cookingrecipeproject.SODIUM";
    public static final String TOTALCARBOHYDRATES = "com.example.cookingrecipeproject.TOTALCARBOHYDRATE";
    public static final String DIETARYFIBER = "com.example.cookingrecipeproject.DIETARYFIBER";
    public static final String PROTEIN = "com.example.cookingrecipeproject.PROTEIN";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button editBtn = (Button) findViewById(R.id.enterBtn);
        editBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                EditText calorieNum = (EditText) findViewById(R.id.calNum);
                String calString = calorieNum.getText().toString();

                EditText fatNum = (EditText) findViewById(R.id.fatNum);
                String fatString = fatNum.getText().toString();

                EditText satFatNum = (EditText) findViewById(R.id.satFatNum);
                String satFatString =  satFatNum.getText().toString();

                EditText cholesterolNum = (EditText) findViewById(R.id.cholesterolNum);
                String cholesterolString = cholesterolNum.getText().toString();

                EditText sodiumNum = (EditText) findViewById(R.id.sodiumNum);
                String sodiumString = sodiumNum.getText().toString();

                EditText totalCarbNum = (EditText) findViewById(R.id.totalCarbNum);
                String totalCarbString = totalCarbNum.getText().toString();

                EditText fiberNum = (EditText) findViewById(R.id.fiberNum);
                String fiberString = fiberNum.getText().toString();

                EditText proteinNum = (EditText) findViewById(R.id.proteinNum);
                String proteinString = proteinNum.getText().toString();

                Intent nutritionPageDisplayIntent = new Intent(getApplicationContext(), NutritionPageDisplay.class);
                nutritionPageDisplayIntent.putExtra(CALORIES, calString);
                nutritionPageDisplayIntent.putExtra(TOTALFAT, fatString);
                nutritionPageDisplayIntent.putExtra(SATFAT, satFatString);
                nutritionPageDisplayIntent.putExtra(CHOLESTEROL, cholesterolString);
                nutritionPageDisplayIntent.putExtra(SODIUM, sodiumString);
                nutritionPageDisplayIntent.putExtra(TOTALCARBOHYDRATES, totalCarbString);
                nutritionPageDisplayIntent.putExtra(DIETARYFIBER, fiberString);
                nutritionPageDisplayIntent.putExtra(PROTEIN, proteinString);


                startActivity(nutritionPageDisplayIntent);
            }
        });



    }
}