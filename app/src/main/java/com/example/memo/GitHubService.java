package com.example.memo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


/**
 * Created by YUNKYUSEOK on 2017-07-03.
 */

public interface GitHubService {

    @GET("/memo/")
    Call<List<Memo>> repoMemo();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-13-124-51-140.ap-northeast-2.compute.amazonaws.com:8000/memo/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
