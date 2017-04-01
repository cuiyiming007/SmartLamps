package com.glexer.smartlamp.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Trice on 2017/3/31.
 */

public class RetrofitEngine {
    private static RetrofitEngine instence;
    Retrofit retrofit;

    public static RetrofitEngine getInstence() {
        if (instence == null) {
            synchronized (RetrofitEngine.class) {
                if (instence == null) {
                    instence = new RetrofitEngine();
                }
            }
        }
        return instence;
    }
    private RetrofitEngine(){
         OkHttpClient client = new OkHttpClient();
         retrofit = new Retrofit.Builder()
                .baseUrl("http://prod.glexer.com:8888/GCloudAPI/api/")
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public SmartStoreService getService() {
        return retrofit.create(SmartStoreService.class);
    }

}
