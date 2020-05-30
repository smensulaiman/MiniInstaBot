package com.miniiinstabot.network;

import me.postaddict.instagram.scraper.cookie.CookieHashSet;
import me.postaddict.instagram.scraper.cookie.DefaultCookieJar;
import me.postaddict.instagram.scraper.interceptor.ErrorInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpClient {

    public HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    public OkHttpClient httpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(getHttpLoggingInterceptor())
            .addInterceptor(new ErrorInterceptor())
            .cookieJar(new DefaultCookieJar(new CookieHashSet()))
            .build();
}
