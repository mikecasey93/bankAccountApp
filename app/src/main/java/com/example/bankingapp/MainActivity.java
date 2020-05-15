package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView titlepage, fullName, userName;
    Button btnSave, btnCancel;

    String SHARED_PREFS = "sharedPrefs";
    String SHARED_PREFS2 = "sharedPrefs2";
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    String userFullName = "";
    String getUserFullName = "";
    String UserName = "";
    String getUserName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         // target element
        titlepage = findViewById(R.id.titlepage);
        fullName = findViewById(R.id.fullName);
        userName = findViewById(R.id.userName);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(userFullName, fullName.getText().toString());
                editor.apply();

                SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFS2,MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferences2.edit();
                editor2.putString(UserName, userName.getText().toString());
                editor2.apply();

                // brings user to new activity/window
                Intent i = new Intent(MainActivity.this, BankAccount.class);
                startActivity(i);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, BankAccount.class);
                startActivity(i);
            }
        });

        // load functions
        loadData();
        updateData();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFS2,MODE_PRIVATE);
        getUserFullName = sharedPreferences.getString(userFullName, "");
        getUserName = sharedPreferences2.getString(UserName,"");
    }

    public void updateData(){
        fullName.setText(getUserFullName);
        userName.setText(getUserName);
    }

}