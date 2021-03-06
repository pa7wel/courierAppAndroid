package com.paul.courierappandroid.API;

/**
 * Created by pawel on 28.05.2017.
 */

public class RouteI {
    private int id;
    private String city;
    private Boolean done;

    public RouteI(int id, String city, Boolean done) {
        this.id = id;
        this.city = city;
        this.done = done;
    }

    public String getCity() {
        return city;
    }

    public int getId() {
        return id;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
