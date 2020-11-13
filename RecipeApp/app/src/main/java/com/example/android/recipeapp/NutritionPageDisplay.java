package com.example.android.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NutritionPageDisplay extends AppCompatActivity {

    /*******************************************************************************************
     * Final constants for grams and milligrams
     *******************************************************************************************/

    final String GRAMS = "g";
    final String MILLIGRAMS = "mg";
    private Button postButton;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_page_display);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        final String userID = user.getUid();
        myRef = mFirebaseDatabase.getReference().child("Users").child(userID);

        /*******************************************************************************************
         * Get the intent being sent to this activity
         * Initialize and declare strings to the numbers given by the user
         * Initialize the textview
         *******************************************************************************************/

        String servingSizeString;
        String caloriesString;
        String totalfatString;
        String satfatString;
        String cholesterolString;
        String sodiumString;
        String totalCarbString;
        String dietaryFiberString;
        String proteinString;

        Intent intent = getIntent();
        servingSizeString = intent.getStringExtra(NutritionEdit.SERVINGSIZE);
        caloriesString = AddGramstoString(intent.getStringExtra(NutritionEdit.CALORIES));
        totalfatString = AddGramstoString(intent.getStringExtra(NutritionEdit.TOTALFAT));
        satfatString = AddGramstoString(intent.getStringExtra(NutritionEdit.SATFAT));
        cholesterolString = AddMilligramstoString(intent.getStringExtra(NutritionEdit.CHOLESTEROL));
        sodiumString = AddMilligramstoString(intent.getStringExtra(NutritionEdit.SODIUM));
        totalCarbString = AddGramstoString(intent.getStringExtra(NutritionEdit.TOTALCARBOHYDRATES));
        dietaryFiberString = AddGramstoString(intent.getStringExtra(NutritionEdit.DIETARYFIBER));
        proteinString = AddMilligramstoString(intent.getStringExtra(NutritionEdit.PROTEIN));

        TextView servingSizeView = findViewById(R.id.servingSizeView);
        TextView caloriesView = findViewById(R.id.calorieView);
        TextView totalFatView = findViewById(R.id.fatView);
        TextView satFatView = findViewById(R.id.saturatedFatView);
        TextView cholesterolView = findViewById(R.id.cholesterolView);
        TextView sodiumView = findViewById(R.id.sodiumView);
        TextView totalCarbohydratesView = findViewById(R.id.carbView);
        TextView dietaryFiberView = findViewById(R.id.fiberView);
        TextView proteinView = findViewById(R.id.proteinView);

        /*******************************************************************************************
         * Set the TextView to display the String numbers
         *******************************************************************************************/

        servingSizeView.setText(servingSizeString);
        caloriesView.setText(caloriesString);
        totalFatView.setText(totalfatString);
        satFatView.setText(satfatString);
        cholesterolView.setText(cholesterolString);
        sodiumView.setText(sodiumString);
        totalCarbohydratesView.setText(totalCarbString);
        dietaryFiberView.setText(dietaryFiberString);
        proteinView.setText(proteinString);

        postButton = findViewById(R.id.postBtn);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRecipe();
            }
        });
    }

    private void postRecipe ()
    {

        /*******************************************************************************************
         * Get values from previous activities
         * Initialize the variables to the values
         *******************************************************************************************/

        final String recipeName = getIntent().getStringExtra("recipeName");
        String description = getIntent().getStringExtra("description");
        String ingredients = getIntent().getStringExtra("ingredients");
        String instructions = getIntent().getStringExtra("instructions");
        String image = getIntent().getStringExtra("image");

        Intent intent = getIntent();
        String servingSizeString = intent.getStringExtra(NutritionEdit.SERVINGSIZE);
        String caloriesString = AddGramstoString(intent.getStringExtra(NutritionEdit.CALORIES));
        String totalfatString = AddGramstoString(intent.getStringExtra(NutritionEdit.TOTALFAT));
        String satfatString = AddGramstoString(intent.getStringExtra(NutritionEdit.SATFAT));
        String cholesterolString = AddMilligramstoString(intent.getStringExtra(NutritionEdit.CHOLESTEROL));
        String sodiumString = AddMilligramstoString(intent.getStringExtra(NutritionEdit.SODIUM));
        String totalCarbString = AddGramstoString(intent.getStringExtra(NutritionEdit.TOTALCARBOHYDRATES));
        String dietaryFiberString = AddGramstoString(intent.getStringExtra(NutritionEdit.DIETARYFIBER));
        String proteinString = AddMilligramstoString(intent.getStringExtra(NutritionEdit.PROTEIN));

        /*******************************************************************************************
         * Adding all the information of the recipe into the database
         *******************************************************************************************/

        myRef.child("recipes").child(recipeName).setValue("");
        myRef.child("recipes").child(recipeName).child("Description").setValue(description);
        myRef.child("recipes").child(recipeName).child("Ingredients").setValue(ingredients);
        myRef.child("recipes").child(recipeName).child("Instructions").setValue(instructions);
        myRef.child("recipes").child(recipeName).child("Nutrition Facts").setValue("");
        myRef.child("recipes").child(recipeName).child("Nutrition Facts").child("Serving Size").setValue(servingSizeString);
        myRef.child("recipes").child(recipeName).child("Nutrition Facts").child("Calories").setValue(caloriesString);
        myRef.child("recipes").child(recipeName).child("Nutrition Facts").child("Total Fat").setValue(totalfatString);
        myRef.child("recipes").child(recipeName).child("Nutrition Facts").child("Saturated Fat").setValue(satfatString);
        myRef.child("recipes").child(recipeName).child("Nutrition Facts").child("Cholesterol").setValue(cholesterolString);
        myRef.child("recipes").child(recipeName).child("Nutrition Facts").child("Sodium").setValue(sodiumString);
        myRef.child("recipes").child(recipeName).child("Nutrition Facts").child("Total Carbohydrates").setValue(totalCarbString);
        myRef.child("recipes").child(recipeName).child("Nutrition Facts").child("Dietary Fiber").setValue(dietaryFiberString);
        myRef.child("recipes").child(recipeName).child("Nutrition Facts").child("Protein").setValue(proteinString);
        myRef.child("recipes").child(recipeName).child("imageuri").setValue(image);

        Toast.makeText(NutritionPageDisplay.this, "Posting " + recipeName + ".", Toast.LENGTH_LONG).show();
        SendUsertoMain();
    }

    /*******************************************************************************************
     * This will send the User back to the home Page.
     *******************************************************************************************/

    private void SendUsertoMain()
    {
        Intent sendUsertoMain = new Intent(NutritionPageDisplay.this, MainActivity.class);
        sendUsertoMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(sendUsertoMain);
        finish();
    }

    /*******************************************************************************************
     * This will add grams (g) at the end of the string.
     *******************************************************************************************/

    public String AddGramstoString (String valueString)
    {
        return valueString + GRAMS;
    }

    /*******************************************************************************************
     * This will add milligrams (mg) at the end of the string.
     *******************************************************************************************/

    public String AddMilligramstoString (String valueString)
    {
        return valueString + MILLIGRAMS;
    }
}