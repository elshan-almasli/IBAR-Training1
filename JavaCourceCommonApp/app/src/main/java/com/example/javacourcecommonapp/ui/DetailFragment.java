package com.example.javacourcecommonapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.javacourcecommonapp.R;
import com.example.javacourcecommonapp.data.model.Movie;
import com.example.javacourcecommonapp.databinding.FragmentDetailBinding;


public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private DetailFragmentArgs detailFragmentArgs;
    private Movie movie;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentDetailBinding.bind(view);

        detailFragmentArgs = DetailFragmentArgs.fromBundle(getArguments());

        movie = detailFragmentArgs.getMovieDetail();

        setupView();
    }

    private void setupView() {
        String url = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();


        Glide.with(requireActivity())
                .load(url)
                .into(binding.imageViewMovieLarge);

        binding.toolbar.setTitle(movie.getTitle());

        binding.textViewInfo.setText(movie.getOverview());

    }
}