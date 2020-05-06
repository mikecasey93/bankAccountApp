package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText uEmail, uPassword;
    Button uLoginBtn;
    TextView uCreateBtn;
    ProgressBar uProgressBar;
    FirebaseAuth uAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uEmail = findViewById(R.id.email);
        uPassword = findViewById(R.id.password);
        uProgressBar = findViewById(R.id.progressBar);
        uAuth = FirebaseAuth.getInstance();
        uLoginBtn = findViewById(R.id.loginbtn);
        uCreateBtn = findViewById(R.id.createText);

        uLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = uEmail.getText().toString().trim();
                String password = uPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    uEmail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    uPassword.setError("Password is required.");
                    return;
                }
                if(password.length() < 6) {
                    uPassword.setError("Password must be at least 6 characters.");
                    return;
                }

                uProgressBar.setVisibility(View.VISIBLE);

                uAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Login.this, "User Create", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Verify.class));
                        }
                        else {
                            Toast.makeText(Login.this, "Error!" + task.getException(), Toast.LENGTH_SHORT).show();
                            uProgressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        uCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }
}
