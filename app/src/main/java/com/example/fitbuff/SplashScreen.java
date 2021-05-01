package com.example.fitbuff;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

public class SplashScreen extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        finish();
    }
}
