package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        String[] nums = {};

        for(int i=0; i < 6; i++){
            Random number = new Random();
            int newNumber;
            newNumber = number.nextInt(10);
            String codeNumber = String.valueOf(newNumber);
            nums[i] = codeNumber;
        }

        final String code = nums[0] + nums[1] + nums[2] + nums[3] + nums[4] + nums[5];
        Toast.makeText(Verify.this, "Your code is" + code, Toast.LENGTH_LONG).show();

        codeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int chances = 3;

                String codePrompt = codeInput.getText().toString().trim();
                if(!codePrompt.equals(code)){
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
