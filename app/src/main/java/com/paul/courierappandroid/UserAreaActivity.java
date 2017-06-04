package com.paul.courierappandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final TextView displayView = (TextView) findViewById(R.id.displayView);

        Intent intent = getIntent();
        String name = intent.getStringExtra("login");
        String token = intent.getStringExtra("authentication_token");

        String message = "Witaj ! jesteś zalogowany na konto: " + name + "\n" +
                "do twojego konta został wygenerowany token: " + token + "\n" +
                "przejdź do widoku trasy: ";
        displayView.setText(message);

    }

    public void routeActivityPage(View view) {
        Intent intent = new Intent(this, RouteActivity.class);
        startActivity(intent);
    }
}
