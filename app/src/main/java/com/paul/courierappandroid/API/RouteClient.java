package com.paul.courierappandroid.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by pawel on 28.05.2017.
 */

public interface RouteClient {
    @GET("v1/routes")
    Call<List<RouteI>> listRoutes();

    @FormUrlEncoded
    @PUT("v1/routes/{id}")
    Call<RouteI> updateRoute(@Path("id") int id, @Field("done") Boolean done);

}
