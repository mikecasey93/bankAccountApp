package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Verify extends AppCompatActivity {

    EditText codeInput;
    Button codeCheck, codeShow;
    String finalCode;
    int chances = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        codeInput = findViewById(R.id.editText);
        codeCheck = findViewById(R.id.button);
        codeShow = findViewById(R.id.button2);
        ArrayList<String> nums = new ArrayList<String>();
        int newNumber = 0;
        String code = "";

        for (int i = 0; i < 6; i++) {
            Random number = new Random();
            newNumber = number.nextInt(10);
            nums.add(Integer.toString(newNumber));
        }
        for (String num : nums) {
            code += num;
        }

        finalCode = code;

        codeShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Verify.this, "Your code is " + finalCode, Toast.LENGTH_LONG).show();
            }
        });
    }
    public void Check(View view){


        String codePrompt = codeInput.getText().toString().trim();
        if (!codePrompt.equals(finalCode)) {
            Toast.makeText(Verify.this, "Incorrect code", Toast.LENGTH_SHORT).show();
            chances -= 1;
        }if (chances == 0) {
                startActivity(new Intent(getApplicationContext(), Lockout.class));
            }
        else if(codePrompt.equals(finalCode)) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
