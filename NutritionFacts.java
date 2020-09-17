package com.example.cookingrecipeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//comment


public class NutritionFacts extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView calorieView = (TextView) findViewById(R.id.calorieView);
        final TextView fatView = (TextView) findViewById(R.id.fatView);
        final TextView proteinView = (TextView) findViewById(R.id.proteinView);
        final TextView carbView = (TextView) findViewById(R.id.carbView);

        calorieView.setVisibility(View.INVISIBLE);
        fatView.setVisibility(View.INVISIBLE);
        proteinView.setVisibility(View.INVISIBLE);
        carbView.setVisibility(View.INVISIBLE);


        Button editBtn = (Button) findViewById(R.id.editBtn);
        editBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                EditText calorieNum = (EditText) findViewById(R.id.calNum);
                EditText fatNum = (EditText) findViewById(R.id.fatNum);
                EditText proteinNum = (EditText) findViewById(R.id.proteinNum);
                EditText totalCarbNum = (EditText) findViewById(R.id.totalCarbNum);


                calorieView.setVisibility(View.VISIBLE);
                fatView.setVisibility(View.VISIBLE);
                proteinView.setVisibility(View.VISIBLE);
                carbView.setVisibility(View.VISIBLE);

                calorieView.setText(calorieNum.getText() + "g");
                fatView.setText(fatNum.getText() + "g");
                proteinView.setText(proteinNum.getText() + "g");
                carbView.setText(totalCarbNum.getText() + "g");
            }
        });



    }
}