package com.supercharge.Homework.controller;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.supercharge.Homework.R;
import com.supercharge.Homework.adapter.MoviesAdapter;
import com.supercharge.Homework.model.MovieBO;
import com.supercharge.Homework.model.response.MoviesResponseBO;
import com.supercharge.Homework.service.MovieService;
import com.supercharge.Homework.util.Utils;
import com.supercharge.Homework.view.DetailActivity;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieController {

    private static final String language = "en_US";

    public void getMovies(final AppCompatActivity activity, final RecyclerView moviesList, final MoviesAdapter adapter, final String search) {

        final String baseUrl = activity.getResources().getString(R.string.base_url);
        final String apiKey = activity.getResources().getString(R.string.api_key);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl).client(httpClient.build()).build();

        MovieService movieService = retrofit.create(MovieService.class);

        Call<MoviesResponseBO> call = movieService.getAllMoviews(apiKey, language, search);

        call.enqueue(new Callback<MoviesResponseBO>() {
            @Override
            public void onResponse(Call<MoviesResponseBO> call, retrofit2.Response<MoviesResponseBO> response) {
                if(response.isSuccessful()) {
                    System.out.println(response.body().getMovies());
                    System.out.println("TAG" + "response 33: " + new Gson().toJson(response.body().getMovies()));

                    if(response.body().getMovies().isEmpty()) {
                        Utils.showDialog("Did not found any movie", activity);
                    }
                    moviesList.setAdapter(new MoviesAdapter(response.body().getMovies(), new MoviesAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(MovieBO item) {
                            Intent myIntent = new Intent(activity, DetailActivity.class);
                            myIntent.putExtra("message", String.valueOf(item.getId()));
                            activity.startActivity(myIntent);
                        }
                    }));

                } else {
                    if(response.code() == 400) {
                        Utils.showDialog("Not found any movie", activity);
                    } else if (response.code() == 401) {
                        Utils.showDialog("Invalid API key", activity);
                    } else {
                        Utils.showDialog("Unexpected error", activity);
                    }

                }

            }

            @Override
            public void onFailure(Call<MoviesResponseBO> call, Throwable t) {
                Utils.showDialog("Could not connect to server", activity);
            }
        });
    }

    public void getMovieById(final AppCompatActivity activity, final String id, final ImageView poster, final TextView title, final TextView overview, final TextView releaseDate, final TextView average) {

        final String baseUrl = activity.getResources().getString(R.string.base_url);
        final String apiKey = activity.getResources().getString(R.string.api_key);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl).client(httpClient.build()).build();

        MovieService movieService = retrofit.create(MovieService.class);

        Call<MovieBO> call = movieService.getMovieById(id, apiKey, language);

        call.enqueue(new Callback<MovieBO>() {
            @Override
            public void onResponse(Call<MovieBO> call, retrofit2.Response<MovieBO> response) {
                if(response.isSuccessful()) {
                    System.out.println(response.body());
                    System.out.println("TAG" + "response 33: " + new Gson().toJson(response.body()));
                    String url = "http://image.tmdb.org/t/p/w500" + response.body().getPicture();
                    Glide.with(poster)
                            .load(url)
                            .apply(RequestOptions.skipMemoryCacheOf(true))
                            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                            .into(poster);
                    title.setText(response.body().getTitle());
                    overview.setText(response.body().getOverview());
                    releaseDate.setText(response.body().getReleaseDate().replace('-', '/'));
                    average.setText(String.valueOf(response.body().getVote_average()));

                } else {
                    if(response.code() == 400) {
                        Utils.showDialog("Not found any movie", activity);
                    } else if (response.code() == 401) {
                        Utils.showDialog("Invalid API key", activity);
                    } else {
                        Utils.showDialog("Unexpected error", activity);
                    }

                }

            }

            @Override
            public void onFailure(Call<MovieBO> call, Throwable t) {
                Utils.showDialog("Could not connect to server", activity);
            }
        });
    }
}
