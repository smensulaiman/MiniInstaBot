package com.miniiinstabot.retrofit;

import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;

public interface APIInterface {

    @Headers({
        "Content-Type: application/json",
        "User-Agent: Mozilla/5.0",
        "Accept-Language: en-US,en;q=0.5"
    })
    
    @POST("image")
    Call<Object> getData(@Body String body);
}
