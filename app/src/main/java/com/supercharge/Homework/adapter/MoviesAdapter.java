package com.supercharge.Homework.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.supercharge.Homework.R;
import com.supercharge.Homework.model.MovieBO;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<MovieBO> movies;

    public MoviesAdapter(List<MovieBO> movies) {
        this.movies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView releaseDate;
        TextView title;
        TextView rating;
        TextView description;
        TextView genres;
        ImageView picture;


        public MovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            rating = itemView.findViewById(R.id.rating);
            picture = itemView.findViewById(R.id.movie_picture);
            description = itemView.findViewById(R.id.description);
        }

        public void bind(MovieBO movie) {
//            releaseDate.setText(movie.getReleaseDate().split("-")[0]);
            title.setText(movie.getTitle());
            rating.setText(String.valueOf(movie.getVote_average()));
            description.setText(String.valueOf(movie.getOverview()));
//            genres.setText("");
            String url = "http://image.tmdb.org/t/p/w500" + movie.getPosterPath();
            Glide.with(itemView)
                    .load(url)
                    .apply(RequestOptions.skipMemoryCacheOf(true))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .into(picture);
        }
    }
}