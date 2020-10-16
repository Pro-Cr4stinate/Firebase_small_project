package com.example.walkingwalking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.walkingwalking.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText UserID;
    private EditText UserEmail;
    private EditText Password;
    private EditText PassConfirm;
    private Button registerbtn;
    private FirebaseAuth firbaseauth;
    private DatabaseReference UserDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerbtn = (Button) findViewById(R.id.btnRegister);
        UserID = (EditText) findViewById(R.id.nameRegister6);
        UserEmail = (EditText)findViewById(R.id.nameRegister5);
        Password = (EditText)findViewById(R.id.nameRegister);
        PassConfirm = (EditText)findViewById(R.id.nameRegister7);
        firbaseauth = FirebaseAuth.getInstance();
        UserDB = FirebaseDatabase.getInstance().getReference("Users");
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserRegist() == false){
                    //x
                }else{
                    ToRegister();
                }
            }
        });
    }
    public void ToRegister(){
        startActivity(new Intent(Register.this, Homepagex.class));
    }
    private boolean UserRegist(){
        final String  username = UserID.getText().toString().trim();
        String  useremail = UserEmail.getText().toString().trim();
        String  password = Password.getText().toString().trim();
        String  passconfirm = PassConfirm.getText().toString().trim();
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "Username must be filled", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (TextUtils.isEmpty(useremail)){
            Toast.makeText(this, "E-mail must be filled", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password must be filled", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!password.equals(passconfirm)){
            Toast.makeText(this, "Password does not match above", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return false;
        }else if(password.length()<8){
            Toast.makeText(this, "Password need minimum of 8 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            firbaseauth.createUserWithEmailAndPassword(useremail, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Register sucess", Toast.LENGTH_SHORT)
                                        .show();
                            }else
                                Toast.makeText(Register.this, "Register fail, try again later", Toast.LENGTH_SHORT)
                                        .show();
                            String id = UserDB.push().getKey();

                            DatabaseReference Unamepush = UserDB.push();
                            Unamepush.child("Username").setValue(UserID);

                        }
                    });
            return true;
        }
    }
}
