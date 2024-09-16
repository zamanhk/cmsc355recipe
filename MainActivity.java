package com.example.sharables;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*******************************************************************************************
        * Button to go to new post page from main method
        *******************************************************************************************/

        Button newPostButton = (Button) findViewById(R.id.postButton);
        newPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newPostIntent = new Intent(getApplicationContext(), NewPost.class);

                startActivity(newPostIntent);

            }
        });

    }
}