package com.example.walkingwalking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton register;
    private  ImageButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (ImageButton) findViewById(R.id.imageButton2);
        register = (ImageButton) findViewById(R.id.imageButton3);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToRegister();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToLogin();
            }
        });
    }
    public void ToLogin(){
        startActivity(new Intent(MainActivity.this, login.class));
    }
    public void ToRegister(){
        startActivity(new Intent(MainActivity.this, Register.class));
    }
}
