package com.example.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    EditText t_email;
    EditText t_pass;
    Button b_signin;
    Button btnSignin;
    Button btnSignupHere;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        fAuth = FirebaseAuth.getInstance();

        t_email = findViewById(R.id.signin_email);
        t_pass = findViewById(R.id.signin_password);
        btnSignin = findViewById(R.id.loginBtn);
        btnSignupHere = findViewById(R.id.registerHereBtn);
        btnSignin.setOnClickListener(view -> loginUser());
        btnSignupHere.setOnClickListener(view -> startActivity(new Intent(SignIn.this, Register.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = fAuth.getCurrentUser();

        if (currentUser == null) {
            Toast.makeText(this, "Anda Belum login!", Toast.LENGTH_SHORT).show();
            return;
        }

//        if (!currentUser.isEmailVerified()) {
//            // pindah ke fragment verfied email
//            return;
//        }

        startActivity(new Intent(SignIn.this, MainActivity.class));


    }

    private void loginUser() {

        String email = t_email.getText().toString();
        String pass = t_pass.getText().toString();

        fAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(SignIn.this, "User SignIn Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(SignIn.this, "User SignIn Successfully ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignIn.this, MainActivity.class));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignIn.this, "ERPRPRRR", Toast.LENGTH_LONG).show();
            }
        });
    }
}
