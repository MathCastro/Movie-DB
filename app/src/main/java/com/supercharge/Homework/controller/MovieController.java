package com.supercharge.Homework.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.supercharge.Homework.R;
import com.supercharge.Homework.adapter.MoviesAdapter;
import com.supercharge.Homework.model.response.MoviesResponseBO;
import com.supercharge.Homework.service.MovieService;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.supercharge.Homework.util.ShowDialogKt.showDialog;

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
                        showDialog("Did not found any movie", activity);
                    }
                    moviesList.setAdapter(new MoviesAdapter(response.body().getMovies()));

                } else {
                    if(response.code() == 400) {
                        showDialog("Not found any movie", activity);
                    } else if (response.code() == 401) {
                        showDialog("Invalid API key", activity);
                    } else {
                        showDialog("Unexpected error", activity);
                    }

                }

            }

            @Override
            public void onFailure(Call<MoviesResponseBO> call, Throwable t) {
//                showDialog("Could not connect to server", activity);
                System.out.println("Failure");
            }
        });
    }
}
