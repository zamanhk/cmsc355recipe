package com.example.android.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instructions);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button addNutrition = findViewById(R.id.nutritionButton);
        addNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText instructionsView = findViewById(R.id.instructionsBox);

                String recipeName = getIntent().getStringExtra("recipeName");
                String description = getIntent().getStringExtra("description");
                String image = getIntent().getStringExtra("image"); // This is the Uri String.  Remember to convert to Uri at the end
                String ingredients = getIntent().getStringExtra("ingredients");
                String instructions = instructionsView.getText().toString();

                if (TextUtils.isEmpty(instructions))
                {
                    Toast.makeText(AddInstructions.this, "Please Enter the Instructions of your Recipe", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(AddInstructions.this, NutritionEdit.class);
                    intent.putExtra("recipeName", recipeName);
                    intent.putExtra("description", description);
                    intent.putExtra("image", image);
                    intent.putExtra("ingredients", ingredients);
                    intent.putExtra("instructions", instructions);
                    startActivity(intent);
                }
            }
        });
    }
}