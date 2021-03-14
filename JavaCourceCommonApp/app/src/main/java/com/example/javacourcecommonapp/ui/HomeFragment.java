package com.example.javacourcecommonapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javacourcecommonapp.MainActivity;
import com.example.javacourcecommonapp.R;
import com.example.javacourcecommonapp.data.model.Movie;
import com.example.javacourcecommonapp.databinding.FragmentHomeBinding;
import com.example.javacourcecommonapp.ui.adapter.MovieAdapter;
import com.example.javacourcecommonapp.ui.viewmodel.MainViewModel;

import java.util.List;


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private FragmentHomeBinding binding;
    private MovieAdapter movieAdapter;
    private MainViewModel mainViewModel;
    private NavController navController;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentHomeBinding.bind(view);

        navController = Navigation.findNavController(view);

        mainViewModel = ((MainActivity) requireActivity()).mainViewModel;

        initRecyclerView();

        getMovieData();
    }

    private void initRecyclerView() {
        movieAdapter = new MovieAdapter();
        binding.recyclerViewMovie.setAdapter(movieAdapter);
        movieAdapter.setOnItemClick(movie -> {
            Log.i(TAG, "initRecyclerView: " + movie.getTitle());
            Bundle bundle = new Bundle();
            bundle.putParcelable("movie_detail", movie);
            navController.navigate(R.id.action_homeFragment_to_detailFragment,bundle);
        });

    }

    private void getMovieData() {
        mainViewModel.getMovieList().observe(requireActivity(), movies -> movieAdapter.setData(movies));
    }


}