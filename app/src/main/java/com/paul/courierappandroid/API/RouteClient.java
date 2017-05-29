package com.paul.courierappandroid.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pawel on 28.05.2017.
 */

public interface RouteClient {
    @GET("v1/routes")
    Call<List<RouteI>> listRoutes();

}
