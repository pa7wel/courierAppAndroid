package com.paul.courierappandroid.API;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.paul.courierappandroid.R;

import java.util.List;

import okhttp3.Route;


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
        // ustawienie wiadomsoci
        TextView textView = (TextView) row.findViewById(R.id.route_text);
        final CheckBox checkBox = (CheckBox) row.findViewById(R.id.checkRoute);
        final RouteI item = values.get(position);
        String message = item.getCity();
        Boolean done = item.getDone();
        textView.setText(message);
        checkBox.setChecked(done);
        // zaznaczenie boxa
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Log.d("TAG", "dziala jestttttt" + position + "checkek:  " + isChecked);
                    //dane do wyslania
                    item.setDone(true);
                    Log.d("TAG", "dow sylania obiekt: " + item);
                    int ajdik = item.getId();
                    Log.d("ID do wyslania", "Do syalnia" + ajdik);

                } else {
                    //checkBox.setChecked(true);
                    System.out.println("Position " + isChecked);
                }
            }
        });






        return row;
    }
}
