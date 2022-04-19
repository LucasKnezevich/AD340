package com.lucasknezevich.AD340;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] btn_names_main = {"Add Event", "Calendar", "Gear List", "Account Info"};

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


        GridView main_grid = findViewById(R.id.main_gridview);
        main_grid.setAdapter(new ButtonAdapter(this));


    }

    public class ButtonAdapter extends BaseAdapter {

        private Context myContext;

        public ButtonAdapter(Context context) {
            myContext = context;
        }

        @Override
        public int getCount() {
            return btn_names_main.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Button button;
            if (convertView == null) {
                button = new Button(myContext);
            } else {
                button = (Button) convertView;
            }

            button.setText(btn_names_main[position]);
            button.setId(position);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast = Toast.makeText(myContext,btn_names_main[position],
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            return button;
        }
    }


}