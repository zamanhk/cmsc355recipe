package com.example.android.recipeapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;    
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RecyclerView postList;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private CircleImageView navProfileImage;
    private TextView navProfileUsername;

    private Button addButton;
    private Button searchButton;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;

    String currentUserId;





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /******************************************************
        *REMOVE AFTER IMPORTING JAMAR'S NAV BAR
         *****************************************************/

        addButton = findViewById(R.id.newPostButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newPostIntent = new Intent(getApplicationContext(), NewPost.class);
                startActivity(newPostIntent);

            }
        });


        /**
         *  Search Bar Button
         */

        searchButton = findViewById(R.id.searchBtn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent searchPageIntent = new Intent(getApplicationContext(), SearchPage.class);
                startActivity(searchPageIntent);
            }
        });





        mAuth =FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawable_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle (MainActivity.this, drawerLayout, R.string.drawer_close,R.string.drawer_open);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
        navProfileImage = (CircleImageView) findViewById(R.id.nav_profile_image);
        navProfileUsername = (TextView) findViewById(R.id.nav_user_full_name);

        userRef.child(currentUserId);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    if (dataSnapshot.hasChild("FullName")) {
                        String fullName = Objects.requireNonNull(dataSnapshot.child("FullName").getValue()).toString();
                        navProfileUsername.setText(fullName);

                    }

                    if (dataSnapshot.hasChild("ProfileImage")) {
                        String image = Objects.requireNonNull(dataSnapshot.child("ProfileImage").getValue()).toString();
                        Picasso.get().load(image).placeholder(R.drawable.profile).into(navProfileImage);

                    }

                }

                else {
                    Toast.makeText(MainActivity.this,
                            "Profile name and image does not exist",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                UserMenuSelector(item);
                return false;
            }
        });

    }

    protected void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null)
        {
            SendUserToLoginActivity();
        }
        else
        {
            CheckUserExistenceForSetup();
        }
    }

    private void CheckUserExistenceForSetup()
    {
        final String CurrentUserId = mAuth.getCurrentUser().getUid();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!dataSnapshot.hasChild(CurrentUserId))
                {
                    SendUsertoSetupActivity();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void SendUsertoSetupActivity()
    {
        Intent AuthNoSetup = new Intent(MainActivity.this, SetupActivity.class);
        AuthNoSetup.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(AuthNoSetup);
        finish();
    }


    private void SendUserToLoginActivity()
    {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void UserMenuSelector(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.nav_profile_image:
                Toast.makeText(this,"Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_home:
                Toast.makeText(this,"Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_find_friends:
                Toast.makeText(this,"Find Friends", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_friends:
                Toast.makeText(this,"Friends", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(this,"Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                mAuth.signOut();
                SendUserToLoginActivity();
                break;
        }
    }
}