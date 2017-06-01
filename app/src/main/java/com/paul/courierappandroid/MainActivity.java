package com.paul.courierappandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginPage(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void routePage(View view) {
        Intent intent = new Intent(this, RouteActivity.class);
        startActivity(intent);
    }

    public void locationPage(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }
}
