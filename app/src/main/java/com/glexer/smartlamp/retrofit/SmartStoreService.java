package com.glexer.smartlamp.retrofit;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Trice on 2017/3/31.
 */

public interface SmartStoreService {

    @POST("user/login")
    @Headers({
            "Content-Type: application/json; charset=utf-8",
            "app_id: 96bf22cb0022408c89c3347804744517",
            "signature: e45133c59e279783e33f4991ecab7c63"
    })
    Call<LoginEntry> login(@Body RequestBody body);

    @POST("user/login")
    @Headers({
            "Content-Type: application/json; charset=utf-8",
            "app_id: 96bf22cb0022408c89c3347804744517",
            "signature: e45133c59e279783e33f4991ecab7c63"
    })
    Call<String> loginString(@Body RequestBody body);
}
