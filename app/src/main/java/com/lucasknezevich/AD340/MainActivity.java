package com.lucasknezevich.AD340;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {

    String[] btn_names_main;
    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = findViewById(R.id.button_login);

        btn_names_main = new String[] {getString(R.string.movies_label)
                , getString(R.string.seattleTrafficCams_label)
                , getString(R.string.seattleTrafficCamMap_label)
                , " Button 4"};

        nameField = findViewById(R.id.editText_username);
        emailField = findViewById(R.id.editText_email);
        passwordField = findViewById(R.id.editText_password);

        loginBtn.setOnClickListener(view -> {
            Toast toast_login = Toast.makeText(getApplicationContext(),
                    getString(R.string.button_submit),Toast.LENGTH_SHORT);
            toast_login.show();
        });

        GridView main_grid = findViewById(R.id.main_gridview);
        main_grid.setAdapter(new ButtonAdapter(this, btn_names_main));
    }


    private void signIn() {

        String username = nameField.getText().toString();
        String email = nameField.getText().toString();
        String password = nameField.getText().toString();

        Log.d("FIREBASE", "signIn");

        // 1 - validate display name, email, and password entries


        // 2 - save valid entries to shared preferences


        // 3 - sign into Firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.d("FIREBASE", "signIn:onComplete:" + task.isSuccessful());

                    if (task.isSuccessful()) {
                        // update profile. displayname is the value entered in UI
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(username)
                                .build();

                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d("FIREBASE", "User profile updated.");
                                            // Go to FirebaseActivity
                                            startActivity(new Intent(MainActivity.this, FirebaseActivity.class));
                                        }
                                    }
                                });

                    } else {
                        Log.d("FIREBASE", "sign-in failed");

                        Toast.makeText(MainActivity.this, "Sign In Failed",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

}