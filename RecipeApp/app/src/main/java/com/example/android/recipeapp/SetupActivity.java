package com.example.android.recipeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity
{
    private EditText userName,fullName,biography;
    private Button saveButton;
    private CircleImageView profileImage;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private StorageReference userProfileImageRef;

    String currentUserId;
    final static int GALLERYPIC = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");

        userName = (EditText) findViewById(R.id.setup_username);
        fullName = (EditText) findViewById(R.id.setup_fullname);
        biography = (EditText) findViewById(R.id.setup_bio);
        profileImage = (CircleImageView) findViewById(R.id.setup_profile);
        saveButton = (Button) findViewById (R.id.setup_saving_button);
        loadingBar = new ProgressDialog(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SaveAccountSetupInformation();
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERYPIC);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERYPIC && resultCode == RESULT_OK && data != null)
        {
            Uri image = data.getData();
            CropImage.activity(image)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);
        }
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK)
            {
                loadingBar.setTitle("Profile Image");
                loadingBar.setMessage("Please wait while we are uploading your profile image");
                loadingBar.show();
                loadingBar.setCanceledOnTouchOutside(true);

                Uri resultUri = result.getUri();

                final StorageReference filePath = userProfileImageRef.child(currentUserId + ".jpg");

                filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                    {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                            {
                                @Override
                                public void onSuccess(Uri uri)
                                {
                                    final String downloadUrl = uri.toString();
                                    userRef.child("ProfileImage").setValue(downloadUrl)
                                            .addOnCompleteListener(new OnCompleteListener<Void>()
                                            {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task)
                                                {
                                                    if(task.isSuccessful())
                                                    {
                                                        Toast.makeText(SetupActivity.   this, "Profile image stored to firebase: "  ,Toast.LENGTH_SHORT).show();
                                                        loadingBar.dismiss();
                                                    }
                                                    else
                                                    {
                                                        String message = task.getException().getMessage();
                                                        Toast.makeText(SetupActivity.   this, "Error: " + message ,Toast.LENGTH_SHORT).show();
                                                        loadingBar.dismiss();
                                                    }
                                                }
                                            });
                                }
                            });
                    }

                });
            }
            else
            {
                Toast.makeText(SetupActivity.   this, "Error: Image could not have been cropped ",Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }
    }



    private void SaveAccountSetupInformation()
    {
        String username = userName.getText().toString();
        String fullname = fullName.getText().toString();
        String bio = biography.getText().toString();

        if(TextUtils.isEmpty(username))
        {
            Toast.makeText(this,"Type your username",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(fullname))
        {
            Toast.makeText(this,"Type your fullname",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(bio))
        {
            Toast.makeText(this,"Type your biography",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Saving your information");
            loadingBar.setMessage("Please wait while we are creating your new account");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            HashMap userMap  = new HashMap();
            userMap.put("Username", username);
            userMap.put("Full Name", fullname);
            userMap.put("Biography", bio);
            userRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task)
                {
                    if(task.isSuccessful())
                    {
                        SendUsertoMainActivity();
                        Toast.makeText(SetupActivity.this, "Account Setup is done",Toast.LENGTH_LONG).show();
                        loadingBar.dismiss();
                    }
                    else
                    {
                        String message = task.getException().getMessage();
                        Toast.makeText(SetupActivity.this, "Error: " + message ,Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });


        }


    }

    private void SendUsertoMainActivity()
    {
        Intent setupFinishtoMain = new Intent(SetupActivity.this, MainActivity.class);
        setupFinishtoMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setupFinishtoMain);
        finish();
    }
}