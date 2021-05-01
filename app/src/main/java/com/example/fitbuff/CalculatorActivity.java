package com.example.fitbuff;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;



public class CalculatorActivity extends AppCompatActivity {

    private TextView mBMITextView, mHeartRateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        mBMITextView = findViewById(R.id.bmi);
        mHeartRateTextView = findViewById(R.id.heart_rate);



        mBMITextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, BMIActivity.class));
            }
        });

        mHeartRateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, HeartRatesActivity.class));
            }
        });

    }


}
