package com.paul.courierappandroid.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by pawel on 02.06.2017.
 */

public interface LocationClient {

    @FormUrlEncoded
    @POST("v1/locations")
    Call<ILocation> createLocation(@Field("longitude") double longitude, @Field("latitude") double latitude);
}
