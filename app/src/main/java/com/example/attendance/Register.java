package com.example.attendance;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.nio.charset.Charset;
import java.util.Random;

public class Register extends AppCompatActivity {

    TextView t_user;
    TextView t_email;
    TextView t_pass;
    Button b_register;
    Button b_signin;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        t_user = findViewById(R.id.username);
        t_email = findViewById(R.id.email);
        t_pass = findViewById(R.id.password);
        b_register = findViewById(R.id.registerBtn);
        b_signin = findViewById(R.id.loginBtn);

        fAuth = FirebaseAuth.getInstance();

        b_register.setOnClickListener(view -> createUser());
        b_signin.setOnClickListener(view -> startActivity(new Intent(Register.this, SignIn.class)));
    }

//    private String setGenToken(){
//        byte[] array = new byte[3];
//        new Random().nextBytes(array);
//        String generatedString1 = new String(array, Charset.forName("UTF-8"));
//        String generatedString2 = new String(array, Charset.forName("UTF-8"));
//        String generatedString3 = new String(array, Charset.forName("UTF-8"));
//        String generatedString = generatedString1+generatedString2+generatedString3;
//
//        return String.valueOf(generatedString.hashCode());
//    }

    private void createUser(){
        String user = t_user.getText().toString();
        String email = t_email.getText().toString();
        String pass = t_pass.getText().toString();

        if (TextUtils.isEmpty(user)){
            t_user.setError("Username cant be empty");
            t_user.requestFocus();
        }
        else if (TextUtils.isEmpty(email)){
            t_email.setError("email cant be empty");
            t_email.requestFocus();
        }
        else if (TextUtils.isEmpty(pass)){
            t_pass.setError("password cant be empty");
            t_pass.requestFocus();
        }
        else{
            fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Register.this, "User Registered Successfully ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this, SignIn.class));
                    }else {
                        Toast.makeText(Register.this, "User Register Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}