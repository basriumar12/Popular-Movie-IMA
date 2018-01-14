package com.blogbasbas.mymovie;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.blogbasbas.mymovie.adapter.AdapterMovie;
import com.blogbasbas.mymovie.adapter.AdapterMovieSearch;
import com.blogbasbas.mymovie.network.ApiClient;
import com.blogbasbas.mymovie.network.ApiService;
import com.blogbasbas.mymovie.network.CONSTANT;
import com.blogbasbas.mymovie.pojo.ResponseMovie;
import com.blogbasbas.mymovie.pojo.ResponseSearch;
import com.blogbasbas.mymovie.pojo.ResultsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMovieActivity extends AppCompatActivity {

    @BindView(R.id.pdFragmentSearch)
    ProgressBar pdFragmentSearch;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.recycler_movie_search)
    RecyclerView recyclerMovieSearch;
    boolean a =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        ButterKnife.bind(this);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {


            recyclerMovieSearch.setLayoutManager(new GridLayoutManager(SearchMovieActivity.this, 2));
        } else {
            recyclerMovieSearch.setLayoutManager(new GridLayoutManager(SearchMovieActivity.this, 4));
        }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMovie();
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void getMovie() {
        String query = edtSearch.getText().toString();
        if (query.isEmpty()){

            edtSearch.setError("tidak bisa kosong");
        }else {

            ApiService service = ApiClient.getClient().create(ApiService.class);
            Call<ResponseSearch> call = service.searchMovie(CONSTANT.APIKEY, CONSTANT.LANGUAGE, query);
            call.enqueue(new Callback<ResponseSearch>() {
                @Override
                public void onResponse(Call<ResponseSearch> call, Response<ResponseSearch> response) {

                    List<ResultsItem> dataMovie = response.body().getResults();
                    String data1 = response.body().getResults().toString();
                    ResponseSearch responseSearch = response.body();
                    Log.d(" ", "onResponse data: " + dataMovie);
                    AdapterMovieSearch adapterMovie = new AdapterMovieSearch(SearchMovieActivity.this, dataMovie);
                    recyclerMovieSearch.setAdapter(adapterMovie);
                }

                @Override
                public void onFailure(Call<ResponseSearch> call, Throwable t) {

                }
            });

        }
    }


}
