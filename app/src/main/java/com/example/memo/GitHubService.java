package com.example.memo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by YUNKYUSEOK on 2017-07-03.
 */

public interface GitHubService {
    @GET("memo")
    Call<List<Memo>> getMemoList();

    @POST("memo")
    @FormUrlEncoded
    Call<Memo> insertMemo(@Field("title") String title,
                          @Field("text") String content);

    @POST("memo")
    @FormUrlEncoded
    Call<Memo> updateMemo(@Field("title") String title,
                          @Field("text") String content,
                          @Field("id") int id);

    @DELETE("memo/{id}")
    @FormUrlEncoded
    Call<Memo> deleteMemo(@Field("id") int id);
}
