package com.paul.courierappandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.paul.courierappandroid.API.RouteAdapter;
import com.paul.courierappandroid.API.RouteClient;
import com.paul.courierappandroid.API.RouteI;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RouteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        String API_BASE_URL = "http://192.168.0.2:3000/";

        final ListView listView = (ListView) findViewById(R.id.list_routes);

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

        RouteClient client =  retrofit.create(RouteClient.class);
        Call<List<RouteI>> call = client.listRoutes();

        call.enqueue(new Callback<List<RouteI>>() {
            @Override
            public void onResponse(Call<List<RouteI>> call, Response<List<RouteI>> response) {
                List<RouteI> dane = response.body();
                Toast.makeText(RouteActivity.this, "Access", Toast.LENGTH_LONG).show();
                listView.setAdapter(new RouteAdapter(RouteActivity.this, dane));

            }

            @Override
            public void onFailure(Call<List<RouteI>> call, Throwable t) {
                Toast.makeText(RouteActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });

    }



}
