package com.example.memo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YUNKYUSEOK on 2017-07-03.
 */

public class ApiClient {

    public static final String BASE_URL =  "http://ec2-13-124-51-140.ap-northeast-2.compute.amazonaws.com:8000/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
