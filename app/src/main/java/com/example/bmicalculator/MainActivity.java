package com.example.bmicalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtWeight, HeightIn, HeightFt;
        Button button;
        TextView Text_Result;

        edtWeight = findViewById(R.id.edtWeight);
        HeightIn = findViewById(R.id.HeightIn);
        HeightFt = findViewById(R.id.HeightFt);
        button = findViewById(R.id.button);
        Text_Result = findViewById(R.id.Text_Result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if any input is empty
                if (edtWeight.getText().toString().isEmpty() || HeightFt.getText().toString().isEmpty() || HeightIn.getText().toString().isEmpty()) {
                    // Show a Toast message to the user if fields are empty
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Parse input values
                    int wt = Integer.parseInt(edtWeight.getText().toString());
                    int ft = Integer.parseInt(HeightFt.getText().toString());
                    int inch = Integer.parseInt(HeightIn.getText().toString());

                    // Convert height in feet and inches to total inches, then calculate BMI
                    int totalInch = (ft * 12) + inch;
                    double totalCm = totalInch * 2.54;
                    double totalM = totalCm / 100;
                    double bmi = wt / (totalM * totalM);
                    double new_bmi = Math.round(bmi * 100.0) / 100.0;

                    // Display result based on BMI value
                    if (bmi > 25) {
                        Text_Result.setText("Your BMI= " + new_bmi + "    " + "You are Overweight");
                    } else if (bmi < 18.5) {
                        Text_Result.setText("Your BMI= " + new_bmi + "    " + "You are Underweight");
                    } else {
                        Text_Result.setText("Your BMI= " + new_bmi + "    " + "You are Normal");
                    }

                } catch (NumberFormatException e) {
                    // Handle cases where the input is not a valid number
                    Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
