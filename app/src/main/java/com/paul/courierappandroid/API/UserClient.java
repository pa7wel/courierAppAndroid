package com.paul.courierappandroid.API;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface UserClient {
    @FormUrlEncoded
    @POST("v1/sessions")
    Call<IUser> userSession(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @DELETE("v1/sessions/{id}")
    Call<IUser> userDestroySession(@Path("id") int id);
}
