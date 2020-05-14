package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class BankAccount extends AppCompatActivity {

    TextView titlepage, fullName, userName, bankone, expired;

    Button btnEdit;

    String SHARED_PREFS = "sharedPrefs";
    String SHARED_PREFS2 = "sharedPrefs2";
    String userFullName = "";
    String getUserFullName;
    String UserName = "";
    String getUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account);
        // target element
        titlepage = findViewById(R.id.titlepage);
        fullName = findViewById(R.id.fullName);
        userName = findViewById(R.id.userName);
        bankone = findViewById(R.id.bankone);
        expired = findViewById(R.id.expired);

        btnEdit = findViewById(R.id.btnEdit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BankAccount.this,MainActivity.class);
                startActivity(i);
            }
        });

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
    public void logout1(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}
