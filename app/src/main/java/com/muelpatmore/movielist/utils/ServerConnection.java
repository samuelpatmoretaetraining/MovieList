package com.muelpatmore.movielist.utils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerConnection {
    private static Retrofit retrofit;
    private OkHttpClient okHttpClient;

    public static RequestInterface getServerConnection() {
        retrofit= new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();
        return retrofit.create(RequestInterface.class);
    }
}
