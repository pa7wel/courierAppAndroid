package com.paul.courierappandroid.API;

/**
 * Created by pawel on 02.06.2017.
 */

public class ILocation {
    private double longitude;
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public ILocation(double x, double y) {
        this.longitude = x;
        this.latitude = y;

    }
}
