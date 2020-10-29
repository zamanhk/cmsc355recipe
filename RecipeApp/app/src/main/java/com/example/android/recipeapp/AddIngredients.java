package com.example.android.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class AddIngredients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button addInstructions = findViewById(R.id.instructionsButton);
        addInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText ingredientsBox = findViewById(R.id.ingredientsBox);

                String recipeName = getIntent().getStringExtra("recipeName");
                String description = getIntent().getStringExtra("description");
                String image = getIntent().getStringExtra("image"); // This is the Uri String.  Remember to convert to Uri at the end
                String ingredients = ingredientsBox.getText().toString();

                if (TextUtils.isEmpty(ingredients))
                {
                    Toast.makeText(AddIngredients.this,"Please Enter the Ingredients of your Recipe", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(AddIngredients.this, AddInstructions.class);
                    intent.putExtra("recipeName", recipeName);
                    intent.putExtra("description", description);
                    intent.putExtra("image", image);
                    intent.putExtra("ingredients", ingredients);
                    startActivity(intent);
                }
            }
        });
    }
}