package com.example.walkingwalking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Upload2 extends AppCompatActivity {
    private Button Setplan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload2);
        Setplan = (Button) findViewById(R.id.Upload);


        Setplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Upload2.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}