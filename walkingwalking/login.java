package com.example.walkingwalking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private EditText eamdrress;
    private EditText password;
    private TextView ForgotPass;
    private Button Login;
    private FirebaseAuth LoginAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eamdrress = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText3);
        LoginAuth = FirebaseAuth.getInstance();
        Login = (Button) findViewById(R.id.button);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userlogin();
            }
        });
    }

    private void userlogin(){
        String email = eamdrress.getText().toString().trim();
        String pass =password.getText().toString().trim();

        if (email.isEmpty()){
            eamdrress.setError("Field required insert email");
            return;
        }
        if (pass.isEmpty()){
            password.setError("Field Required, insert password");
            return;
        }

        LoginAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(login.this, Homepagex.class));
                }else {
                    Toast.makeText(login.this, "Incorrect email or password", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

    }
}


