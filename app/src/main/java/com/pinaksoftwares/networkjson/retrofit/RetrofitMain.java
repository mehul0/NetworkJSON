package com.pinaksoftwares.networkjson.retrofit;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.pinaksoftwares.networkjson.MainActivity;
import com.pinaksoftwares.networkjson.R;
import com.pinaksoftwares.networkjson.retrofit.adapter.MovieAdapter;
import com.pinaksoftwares.networkjson.retrofit.model.Movie;
import com.pinaksoftwares.networkjson.retrofit.model.MovieResponse;
import com.pinaksoftwares.networkjson.retrofit.rest.ApiClient;
import com.pinaksoftwares.networkjson.retrofit.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitMain extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String API_KEY = "07d9a20df3d9b6358233c4b14962aa5d";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retro_activity_retrofit_main);

        if (API_KEY.isEmpty()){
            Toast.makeText(this, "API KEY Not Found", Toast.LENGTH_SHORT).show();
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);*/

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiInterface.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MovieAdapter(movies, R.layout.retro_list_item_movie, getApplicationContext()));
                Log.d(TAG, "NO of Movie received : " + movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }
}
