package com.lucasknezevich.AD340;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

public class ButtonAdapter extends BaseAdapter {

    private Context myContext;
    private String[] names;

    public ButtonAdapter(Context context, String[] names) {
        myContext = context;
        this.names = names;
    }

    @Override
    public int getCount() {
        return names.length;
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

        button.setText(names[position]);
        button.setId(position);
        button.setOnClickListener(view -> {
            Toast toast = Toast.makeText(myContext,names[position], Toast.LENGTH_SHORT);
            toast.show();
        });
        return button;
    }
}