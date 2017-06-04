package com.paul.courierappandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.paul.courierappandroid.API.IUser;
import com.paul.courierappandroid.API.RouteAdapter;
import com.paul.courierappandroid.API.RouteClient;
import com.paul.courierappandroid.API.RouteI;
import com.paul.courierappandroid.API.UserClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText login = (EditText) findViewById(R.id.login);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button loginBtn = (Button) findViewById(R.id.loginBtn);
//
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final String email = login.getText().toString();
//                final String pass = password.getText().toString();
//
//                Response.Listener<String> responseListener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            //boolean success = jsonResponse.getBoolean("success");
//
//                            if (true) {
//                                String email = jsonResponse.getString("email");
//                                String authenticationToken = jsonResponse.getString("authentication_token");
//
//
//                                SharedPreferences sharedPref =
//                                        PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
//                                SharedPreferences.Editor editor = sharedPref.edit();
//                                editor.putString("X-User-Email", email);
//                                editor.putString("X-User-Token", authenticationToken);
//                                editor.commit();
//
//
//                                Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
//                                intent.putExtra("login", email);
//                                intent.putExtra("authentication_token", authenticationToken);
//
//                                LoginActivity.this.startActivity(intent);
//
//                            } else {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                                builder.setMessage("LoginActivity failed")
//                                        .setNegativeButton("Retry", null)
//                                        .create()
//                                        .show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                };
//
//                LoginRequest loginRequest = new LoginRequest(email, pass, responseListener);
//                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
//                queue.add(loginRequest);
//            }
//        });

            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String API_BASE_URL = "http://192.168.0.2:3000/";

                    final EditText loginInput = (EditText) findViewById(R.id.login);
                    final EditText passwordInput = (EditText) findViewById(R.id.password);

                    final String email = loginInput.getText().toString();
                    final String password = passwordInput.getText().toString();

                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

                    Retrofit.Builder builder =
                            new Retrofit.Builder()
                                    .baseUrl(API_BASE_URL)
                                    .addConverterFactory(
                                            GsonConverterFactory.create()
                                    );

                    Retrofit retrofit = builder
                            .client(httpClient.build())
                            .build();

                    UserClient client =  retrofit.create(UserClient.class);
                    Call<IUser> call = client.userSession(email, password);

                    call.enqueue(new Callback<IUser>() {
                        @Override
                        public void onResponse(Call<IUser> call, retrofit2.Response<IUser> response) {
                            IUser dane = response.body();
                            Toast.makeText(LoginActivity.this, "Zostałeś zalogowany poprawnie", Toast.LENGTH_LONG).show();

                            SharedPreferences sharedPref =
                                PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("X-User-Email", dane.getEmail());
                            editor.putString("X-User-Token", dane.getAuth_token());
                            editor.commit();

                            Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                intent.putExtra("login", dane.getEmail());
                                intent.putExtra("authentication_token", dane.getAuth_token());

                                LoginActivity.this.startActivity(intent);

                        }

                        @Override
                        public void onFailure(Call<IUser> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Logowanie nie powiodło się", Toast.LENGTH_LONG).show();
                        }
                    });
                };
            });

};
};
