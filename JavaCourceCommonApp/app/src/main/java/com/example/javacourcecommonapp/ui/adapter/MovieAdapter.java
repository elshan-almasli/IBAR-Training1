package com.example.javacourcecommonapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.javacourcecommonapp.data.model.Movie;
import com.example.javacourcecommonapp.databinding.ItemListMovieBinding;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<Movie> movieList;

    public MovieAdapter(){
        movieList = new ArrayList<>();
    }

    private SetOnItemClickListener setOnItemClickListener;

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemListMovieBinding binding = ItemListMovieBinding.inflate(layoutInflater,parent,false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.onBind(movieList.get(position));
        holder.binding.getRoot().setOnClickListener(v -> {
            setOnItemClickListener.onClick(movieList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setData(List<Movie> movies){
        movieList.clear();
        movieList.addAll(movies);
        notifyDataSetChanged();
    }

    public void setOnItemClick(SetOnItemClickListener setOnItemClickListener){
        this.setOnItemClickListener = setOnItemClickListener;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        private ItemListMovieBinding binding;

        public MovieViewHolder(ItemListMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Movie movie){
            String url = "https://image.tmdb.org/t/p/w500"+movie.getPosterPath();
            binding.textViewMovieTitle.setText(movie.getTitle());
            binding.textViewMoviePoint.setText(Double.toString(movie.getPopularity()));
            Glide.with(binding.imageViewMovie.getContext())
                    .load(url)
                    .into(binding.imageViewMovie);
        }
    }

    public interface SetOnItemClickListener{
        void onClick(Movie movie);
    }

}
