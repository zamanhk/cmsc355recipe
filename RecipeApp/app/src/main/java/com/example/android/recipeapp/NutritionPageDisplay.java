package com.example.android.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NutritionPageDisplay extends AppCompatActivity {

    /*****************************
     * final Constants for grams and milligrams
     *
     ******************************/

    final String GRAMS = "g";
    final String MILIGRAMS = "mg";

    private String saveCurrentDate, saveCurrentTime, postRandomName;
    private Uri image;
    private StorageReference postImageReference;
    private Button postButton;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef,postRef;



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

        TextView servingSizeView = findViewById(R.id.servingSizeView);
        TextView caloriesView = findViewById(R.id.calorieView);
        TextView totalFatView = findViewById(R.id.fatView);
        TextView satFatView = findViewById(R.id.saturatedFatView);
        TextView cholesterolView = findViewById(R.id.cholesterolView);
        TextView sodiumView = findViewById(R.id.sodiumView);
        TextView totalCarbohydratesView = findViewById(R.id.carbView);
        TextView dietaryFiberView = findViewById(R.id.fiberView);
        TextView proteinView = findViewById(R.id.proteinView);

        /*****************************
         * Set the TextView to display the String numbers
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


        postButton = findViewById(R.id.postBtn);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRecipe();
                //SendUsertoMain();
                //ImagetoFirebase();
            }
        });

    }

    private void postRecipe ()
    {
        // Get values from other activities
        // Initialize the variables to the values

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

        String recipeName = getIntent().getStringExtra("recipeName");
        String description = getIntent().getStringExtra("description");
        String image = getIntent().getStringExtra("image"); // This is the Uri String.  Remember to convert to Uri at the end
        String ingredients = getIntent().getStringExtra("ingredients");
        String instructions = getIntent().getStringExtra("instructions");

        //Uri imageURI = Uri.parse(image); //Converting the String to the Uri

        myRef.child("recipes").child(recipeName).setValue("");
        //myRef.child("recipes").child(recipeName).child("Image").setValue(image); //Adding Image is not sending the right path.
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



        Toast.makeText(NutritionPageDisplay.this, "Posting " + recipeName + ".", Toast.LENGTH_LONG).show();

    }

    private void SendUsertoMain()
    {
        Intent sendUsertoMain = new Intent(NutritionPageDisplay.this, MainActivity.class);
        sendUsertoMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(sendUsertoMain);
        finish();
    }

    private void ImagetoFirebase()
    {
        Calendar callforDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM/dd/yyyy");
        saveCurrentDate = currentDate.format(callforDate.getTime());

        Calendar callforTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrentTime = currentTime.format(callforTime.getTime());

        postRandomName = saveCurrentDate + saveCurrentTime;

        StorageReference filepath = postImageReference.child("Post Images").child(image.getLastPathSegment() + postRandomName + ".jpg");
        filepath.putFile(image).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(NutritionPageDisplay.this, "Image uploaded successfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    String message = task.getException().getMessage();
                    Toast.makeText(NutritionPageDisplay.this, "Error: " + message ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}