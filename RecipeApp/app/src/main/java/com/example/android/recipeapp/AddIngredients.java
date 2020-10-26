package com.example.android.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

public class AddIngredients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button saveIngredients = findViewById(R.id.saveButton);
        saveIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText ingredientsView = (EditText) findViewById(R.id.ingredientsBox);
                String ingredients = ingredientsView.getText().toString();



                Intent intent = new Intent(AddIngredients.this, NewPost.class);
                intent.putExtra("ingredients", ingredients);
                startActivity(intent);
            }
        });
    }
}