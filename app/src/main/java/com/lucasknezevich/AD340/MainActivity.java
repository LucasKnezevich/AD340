package com.lucasknezevich.AD340;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] btn_names_main = {"Movies", "Calendar", "Gear List", "Account Info"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = findViewById(R.id.button_login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast_login = Toast.makeText(getApplicationContext(),
                        getString(R.string.button_submit),Toast.LENGTH_SHORT);
                toast_login.show();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
                startActivity(intent);
            }
        });

        GridView main_grid = findViewById(R.id.main_gridview);
        main_grid.setAdapter(new ButtonAdapter(this, btn_names_main));

//        Button movieBtn = findViewById(0);
//        movieBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}