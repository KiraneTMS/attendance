package com.example.attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    TextView t_email;
    TextView t_pass;
    Button b_signin;
    Button btnSignin;
    Button btnSignupHere;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        fAuth = FirebaseAuth.getInstance();

        btnSignin = findViewById(R.id.loginBtn);
        btnSignupHere = findViewById(R.id.registerHereBtn);
        btnSignin.setOnClickListener(view -> loginUser());
        btnSignupHere.setOnClickListener(view -> startActivity(new Intent(SignIn.this, Register.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = fAuth.getCurrentUser();
        if (currentUser != null){
            startActivity(new Intent(SignIn.this, MainActivity.class));
        }
    }
    private void loginUser(){
        String email = t_email.getText().toString();
        String pass = t_pass.getText().toString();

        if (TextUtils.isEmpty(email)){
            t_email.setError("email cant be empty");
            t_email.requestFocus();
        }
        else if (TextUtils.isEmpty(pass)){
            t_pass.setError("password cant be empty");
            t_pass.requestFocus();
        }
        else{
            fAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignIn.this, "User SignIn Successfully ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignIn.this, MainActivity.class));
                    }else {
                        Toast.makeText(SignIn.this, "User SignIn Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}