package com.paul.courierappandroid.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.paul.courierappandroid.LoginActivity;
import com.paul.courierappandroid.R;
import com.paul.courierappandroid.RouteActivity;

import java.util.List;

import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RouteAdapter extends ArrayAdapter<RouteI> {
    private Context context;
    public List<RouteI> values;

    public RouteAdapter(Context context, List<RouteI> values) {
        super(context, R.layout.route_view, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.route_view, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.route_text);
        final CheckBox checkBox = (CheckBox) row.findViewById(R.id.checkRoute);
        final RouteI item = values.get(position);
        String message = item.getCity();
        Boolean done = item.getDone();
        textView.setText(message);
        checkBox.setChecked(done);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Log.d("TAG", "dziala jestttttt" + position + "checkek:  " + isChecked);
                    //dane do wyslania
                    if (item.getDone() == false) {
                        item.setDone(true);
                    } else {
                        item.setDone(false);
                    }

                    //item.setDone(true);
                    Log.d("TAG", "dow sylania obiekt: " + item);
                    int item_id = item.getId();
                    Log.d("ID do wyslania", "Do syalnia" + item_id);
                    Boolean done = item.getDone();
                    updateRequestRoute(item_id, done);

                } else {
                    System.out.println("Position " + isChecked);
                }
            }
        });
        return row;
    }

    String API_BASE_URL = "http://192.168.0.2:3000/";

    public void updateRequestRoute(int id, Boolean done) {
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit = builder.build();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String emailNew = sharedPref.getString("X-User-Email", "");
        String tokenNew = sharedPref.getString("X-User-Token", "");


        RouteClient client =  retrofit.create(RouteClient.class);
        Call<RouteI> call = client.updateRoute(id, done, emailNew, tokenNew);

        call.enqueue(new Callback<RouteI>() {
            @Override
            public void onResponse(Call<RouteI> call, Response<RouteI> response) {
                Log.d("POST", "DZIALA GOOOOD !!!!!!!!!!!!!!!!!!!" + response);
            }

            @Override
            public void onFailure(Call<RouteI> call, Throwable t) {
                Log.d("ERROR", "nie dziala !!!!!!!!!!!!!!!!!!!");
            }
        });
    }
}
