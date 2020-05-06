package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class Verify extends AppCompatActivity {

    EditText codeInput;
    Button codeCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        codeInput = findViewById(R.id.editText);
        codeCheck = findViewById(R.id.button);
        ArrayList<String> nums = new ArrayList<String>();
        int newNumber = 0;
        String code = "";

        for(int i=0; i < 6; i++){
            Random number = new Random();
            newNumber = number.nextInt(10);
            nums.add(Integer.toString(newNumber));
        }
        for(String num:nums){
            code += num;
        }
        Toast.makeText(Verify.this, "Your code is" + code, Toast.LENGTH_LONG).show();

        final String finalCode = code;
        codeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int chances = 3;

                String codePrompt = codeInput.getText().toString().trim();
                if(!codePrompt.equals(finalCode)){
                    Toast.makeText(Verify.this, "Incorrect code", Toast.LENGTH_SHORT).show();
                    chances -= 1;
                    if(chances == 0){
                        startActivity(new Intent(getApplicationContext(), Lockout.class));
                    }
                }
                else{
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }

            }
        });

    }
}
