package com.paul.courierappandroid;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.paul.courierappandroid.API.ILocation;
import com.paul.courierappandroid.API.LocationClient;
import com.paul.courierappandroid.API.RouteClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationActivity extends AppCompatActivity {

    private Button b;
    private TextView t;
    private LocationManager locationManager;
    private LocationListener listener;
    private double LocationToSentX;
    private double LocationToSentY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        t = (TextView) findViewById(R.id.displayLocation);
        b = (Button) findViewById(R.id.sentLocation);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                t.append("\n " + location.getLongitude() + " " + location.getLatitude());
                double LocationToSentX = location.getLongitude();
                double LocationToSentY = location.getAltitude();

                // POST to db with x,y location
                createLocation(LocationToSentX, LocationToSentY);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        configure_button();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    void configure_button(){
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission
                locationManager.requestLocationUpdates("gps", 5000, 0, listener);
            }
        });
    }


    public void createLocation(double longitude, double latitude) {
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl("http://192.168.0.2:3000")
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit = builder.build();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(LocationActivity.this);
        String emailNew = sharedPref.getString("X-User-Email", "");
        String tokenNew = sharedPref.getString("X-User-Token", "");

        Log.d("SPRAWDZAMY -------_>", "user: " + emailNew);
        Log.d("SPRAWDZAMY -------_>", "token: " + tokenNew);

        LocationClient client =  retrofit.create(LocationClient.class);
        Call<ILocation> call = client.createLocation(longitude, latitude, emailNew, tokenNew);

        call.enqueue(new Callback<ILocation>() {
            @Override
            public void onResponse(Call<ILocation> call, Response<ILocation> response) {
                Log.d("POST", "DZIALA GOOOOD !!!!!!!!!!!!!!!!!!!" + response);
            }

            @Override
            public void onFailure(Call<ILocation> call, Throwable t) {
                Log.d("ERROR", "nie dziala !!!!!!!!!!!!!!!!!!!");
            }
        });
    }
}
