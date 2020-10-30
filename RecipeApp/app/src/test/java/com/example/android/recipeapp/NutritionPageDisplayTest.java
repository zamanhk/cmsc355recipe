package com.example.android.recipeapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class NutritionPageDisplayTest {

    @Test
    public void addGramstoString()
    {
        String checker = "15";
        NutritionPageDisplay myGrams = new NutritionPageDisplay();
        assertTrue(myGrams.AddGramstoString(checker).contains("g"));
    }

    @Test
    public void addMilligramstoString()
    {
        String checker = "15";
        NutritionPageDisplay myGrams = new NutritionPageDisplay();
        assertTrue(myGrams.AddMilligramstoString(checker).contains("mg"));
    }
}