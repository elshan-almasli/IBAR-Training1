package com.anushka.tmdbclient.presenter.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anushka.tmdbclient.BaseApp
import com.anushka.tmdbclient.R
import com.anushka.tmdbclient.databinding.ActivityMovieBinding
import com.anushka.tmdbclient.presenter.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    @Inject
    lateinit var factory: MovieViewModelFactory

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        (application as Injector)
            .createMovieSubComponent()
            .inject(this)
        movieViewModel = ViewModelProvider(this,factory).get(MovieViewModel::class.java)

        initRecyclerView()

        initObserver()


    }

    private fun initObserver() {
        movieViewModel.getMovie().observe(this, Observer { movie ->

            movie?.let {
                Log.i(TAG, "initObserver: ${movie[0].overview}")
                movieAdapter.setList(it)
                movieAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun initRecyclerView(){
        movieAdapter = MovieAdapter();
        binding.recyclerViewMovie.apply {
            adapter = movieAdapter
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_update,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_update -> {
                updateMovies()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun updateMovies(){
        binding.movieProgressBar.visibility = View.VISIBLE
        val response = movieViewModel.updateMovie()
        response.observe(this, Observer {
            if(it!=null){
                movieAdapter.setList(it)
                movieAdapter.notifyDataSetChanged()
                binding.movieProgressBar.visibility = View.GONE
            }else{
                binding.movieProgressBar.visibility = View.GONE
            }
        })
    }

    companion object{
        private const val TAG = "MovieActivity"
    }
}