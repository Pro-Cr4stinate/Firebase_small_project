package com.example.walkingwalking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Calendar extends AppCompatActivity {
    private ImageView upplan;
    private ImageView Home;
    private ImageView Diary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        upplan = (ImageView) findViewById(R.id.Add);
        Home = (ImageView) findViewById(R.id.homex);
        Diary = (ImageView) findViewById(R.id.travDiary);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Calendar.this, Homepagex.class));
            }
        });
        Diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Calendar.this, TravDiary.class));
            }
        });
        upplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Calendar.this, Upload2.class));
            }
        });
    }
}