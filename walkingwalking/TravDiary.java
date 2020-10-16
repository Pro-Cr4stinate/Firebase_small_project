package com.example.walkingwalking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;

public class TravDiary extends AppCompatActivity {
 //   private FirebaseStorage tostorage;
    private ImageView homepage;
    private ImageView calendar;
    private ImageView upload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trav_diary);
        homepage = (ImageView) findViewById(R.id.home);
        upload = (ImageView)findViewById(R.id.add);
        calendar = (ImageView)findViewById(R.id.Calendar);

        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToHompage();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToAdd();
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                 public void onClick(View v) {
                                   ToCalendar();
                             }
                       }
        );

    }

    public void ToHompage(){
        startActivity(new Intent(TravDiary.this, Homepagex.class));
    }

    public void ToAdd(){
        startActivity(new Intent(TravDiary.this, Upload.class));
    }

     public void ToCalendar(){
         startActivity(new Intent(TravDiary.this, Calendar.class));
     }
}



