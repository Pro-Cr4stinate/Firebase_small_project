package com.example.walkingwalking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class profilex extends AppCompatActivity {

    private ImageView sixmonth;
    private ImageView twelvemonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilex);

        sixmonth = (ImageView) findViewById(R.id.Sixmonths);
        twelvemonth = (ImageView) findViewById(R.id.Twelvemonths);

        sixmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profilex.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
        twelvemonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profilex.this, "Success", Toast.LENGTH_SHORT).show();

            }
        });

    }
}