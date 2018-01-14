package com.blogbasbas.mymovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.blogbasbas.mymovie.adapter.AdapterMovie;
import com.blogbasbas.mymovie.network.ApiClient;
import com.blogbasbas.mymovie.network.ApiService;
import com.blogbasbas.mymovie.network.CONSTANT;
import com.blogbasbas.mymovie.pojo.ResponseMovie;
import com.blogbasbas.mymovie.pojo.ResultsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContohMovieActivity extends AppCompatActivity {

    @BindView(R.id.rv_rcyclerview)
    RecyclerView rvRcyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contoh_movie);
        ButterKnife.bind(this);
        rvRcyclerview.setLayoutManager(new GridLayoutManager(ContohMovieActivity.this,2));
        getMovie();
    }

    private void getMovie() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ResponseMovie> call = service.getUpComingMoviee(CONSTANT.APIKEY, CONSTANT.LANGUAGE);
        call.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {

                List<ResultsItem> dataMovie = response.body().getResults();
                String data1 = response.body().getResults().toString();
                ResponseMovie responseMovie = response.body();
                Log.d(" ", "onResponse data: "+dataMovie);


                AdapterMovie adapterMovie = new AdapterMovie(ContohMovieActivity.this,dataMovie);
                rvRcyclerview.setAdapter(adapterMovie);

            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {
                Log.d("", "onFailure: "+t.toString());


            }
        });

    }}
