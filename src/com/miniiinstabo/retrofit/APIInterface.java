package com.miniiinstabo.retrofit;

import com.google.gson.JsonObject;
import java.util.HashMap;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface APIInterface {

    @FormUrlEncoded
    @POST("in.php")
    Call<String> in(
            @Field("method") String method,
            @Field("key") String key,
            @Field("body") String body,
            @Field("header_acao") String headerAcao
    );

    // @FormUrlEncoded
    @GET("res.php")
    Call<String> out(
            @Query("key") String key,
            @Query("action") String action,
            @Query("id") String id,
            @Query("header_acao") String headerAcao
    );

    @POST("GAPolandAppt/Account/CheckSeatAllotment")
    Call<String> getEarliestVisaSlotDate(
            @Header("headers") HashMap<String, String> headers,
            @Body JsonObject json
    );

}
