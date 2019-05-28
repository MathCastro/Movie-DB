package com.supercharge.Homework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.supercharge.Homework.R
import com.supercharge.Homework.model.MovieBO

class MoviesAdapter(private val movies: List<MovieBO>, private val listener: OnItemClickListener): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: MovieBO)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.get(position), listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_movie_item, parent, false);
        return MovieViewHolder(view);
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.title)
        var rating: TextView = itemView.findViewById(R.id.rating)
        var description: TextView = itemView.findViewById(R.id.description)
        var picture: ImageView = itemView.findViewById(R.id.movie_picture)


        fun bind(movie: MovieBO, listener: OnItemClickListener) {
            title.text = movie.title
            rating.text = movie.vote_average.toString()
            description.text = movie.overview.toString()
            val url = "http://image.tmdb.org/t/p/w500" + movie.posterPath
            Glide.with(itemView)
                .load(url)
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(picture)
            itemView.setOnClickListener { listener.onItemClick(movie) }
        }
    }

}