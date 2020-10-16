package com.example.walkingwalking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Homepagex extends AppCompatActivity {
    private ImageView Profile;
    private TextView Logout;
    private TextView User;
    private ImageView diary;
    private ImageView calendar;
    private DatabaseReference Uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepagex);
        Logout = (TextView) findViewById(R.id.textView);
        User = (TextView) findViewById(R.id.textView2);
        Profile = (ImageView) findViewById(R.id.imageView);
        diary = (ImageView)findViewById(R.id.diary);
        calendar=(ImageView)findViewById(R.id.kalender);

        Uname = FirebaseDatabase.getInstance().getReference("Users");

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToProfile();
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToLogin();
            }
        });
        diary.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         ToDiary();
                                     }
                                 });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToCalendar();
            }
        });

//        Uname.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String user = dataSnapshot.child("Username").getValue().toString();
//                User.setText(user);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


    }
    public void ToProfile(){
        startActivity(new Intent(Homepagex.this, profilex.class));
    }
    public void ToLogin(){
        startActivity(new Intent(Homepagex.this, login.class));
    }
    public void ToDiary(){
        startActivity(new Intent(Homepagex.this, TravDiary.class));
    }
    public void ToCalendar(){
        startActivity(new Intent(Homepagex.this, Calendar.class));
    }
}

