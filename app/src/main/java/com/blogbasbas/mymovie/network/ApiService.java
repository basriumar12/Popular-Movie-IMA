package com.blogbasbas.mymovie.network;

import com.blogbasbas.mymovie.pojo.ResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User on 14/01/2018.
 */

public interface ApiService {
    @GET("movie/upcoming")
    Call<ResponseMovie> getUpcomingMovie (@Query("api_key")String apikey,
                                            @Query("language")String language);
}
