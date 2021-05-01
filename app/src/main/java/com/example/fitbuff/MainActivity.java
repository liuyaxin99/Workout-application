package com.example.fitbuff;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

public class MainActivity extends AppCompatActivity
{
    private ImageButton btnMusic;
    private Button dn;
    private Button btnbmi;
    private Button btnChrono;
    private Button btnWeight;
    private Button btnweightdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dn = (Button) findViewById(R.id.datanext);
        btnMusic = (ImageButton) findViewById(R.id.musicButton);
        btnbmi = (Button) findViewById(R.id.calculator);
        btnChrono = (Button) findViewById(R.id.action_chrono);
        btnWeight = (Button) findViewById(R.id.weight);
        btnweightdb = findViewById(R.id.weightdb);


        dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent1 = new Intent(MainActivity.this, Activity_ViewPager.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(MainActivity.this, v, "testAnimation");
                    MainActivity.this.startActivity(intent1, options.toBundle());
                } else {
                    startActivity(intent1);
                }
            }
        });


        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SongsList.class));
            }
        });

        btnbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
            }
        });

        btnChrono.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                ChronoDialogbox cdd = new ChronoDialogbox(MainActivity.this);
                cdd.show();

            }
        });

        btnWeight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, profile.class));
            }
        });

        btnweightdb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RecordActivity.class));
            }
        });


    }}
