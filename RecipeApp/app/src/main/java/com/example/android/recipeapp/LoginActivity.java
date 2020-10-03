package com.example.android.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    private Button LoginButton;
    private EditText UserEmail;
    private EditText UserPassword;
    private TextView NewAccountLink;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        NewAccountLink = (TextView) findViewById (R.id.register_link);
        UserEmail = (EditText) findViewById(R.id.login_email);
        UserPassword = (EditText) findViewById(R.id.login_password);
        LoginButton = (Button) findViewById(R.id.login_button);
        loadingBar = new ProgressDialog(this);


        NewAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendUsertoRegisterActivity();
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AllowUsertoLogIn();
            }
        });

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            SendUsertoMainActivity();
        }
    }

    private void AllowUsertoLogIn()
    {
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Type your email",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Type your password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login");
            loadingBar.setMessage("Please wait for the sign in to be completed");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        SendUsertoMainActivity();
                        Toast.makeText(LoginActivity.this, "Successfully logged in",Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                    else
                    {
                        String message = task.getException().getMessage();
                        Toast.makeText(LoginActivity.this, "Error: " + message ,Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }

    }

    private void SendUsertoMainActivity()
    {
        Intent SendToMainIntent = new Intent(LoginActivity.this, MainActivity.class);
        SendToMainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(SendToMainIntent);
        finish();
    }

    private void SendUsertoRegisterActivity()
    {
        Intent RegisterIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(RegisterIntent);
    }
}