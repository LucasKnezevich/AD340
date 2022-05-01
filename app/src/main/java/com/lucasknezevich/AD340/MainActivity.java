package com.lucasknezevich.AD340;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] btn_names_main = {"Movies", "Seattle 🚗 Cams", "Button 3", " Button 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = findViewById(R.id.button_login);

        loginBtn.setOnClickListener(view -> {
            Toast toast_login = Toast.makeText(getApplicationContext(),
                    getString(R.string.button_submit),Toast.LENGTH_SHORT);
            toast_login.show();
        });

        GridView main_grid = findViewById(R.id.main_gridview);
        main_grid.setAdapter(new ButtonAdapter(this, btn_names_main));
    }
}