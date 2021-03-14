package com.anushka.tmdbclient.presenter.tvshow

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anushka.tmdbclient.R
import com.anushka.tmdbclient.databinding.ActivityMovieBinding
import com.anushka.tmdbclient.databinding.ActivityTvShowBinding
import com.anushka.tmdbclient.presenter.di.Injector
import com.anushka.tmdbclient.presenter.movie.MovieAdapter
import com.anushka.tmdbclient.presenter.movie.MovieViewModel
import com.anushka.tmdbclient.presenter.movie.MovieViewModelFactory
import javax.inject.Inject

class TvShowActivity: AppCompatActivity() {

    private lateinit var binding: ActivityTvShowBinding

    @Inject
    lateinit var factory: TvShowViewModelFactory

    private lateinit var tvshowViewModel: TvShowViewModel

    private lateinit var tvshowAdapter: TvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)

        (application as Injector)
            .createTVSHOWSubComponent()
            .inject(this)
        tvshowViewModel = ViewModelProvider(this,factory).get(TvShowViewModel::class.java)

        initRecyclerView()

        initObserver()


    }

    private fun initObserver() {
        tvshowViewModel.getTvShows().observe(this, Observer { tvshow ->

            tvshow?.let {
                Log.i(TAG, "initObserver: ${tvshow[0].overview}")
                tvshowAdapter.setList(it)
                tvshowAdapter.notifyDataSetChanged()
            }
        })
    }

    fun initRecyclerView(){
        tvshowAdapter = TvAdapter();
        binding.recyclerViewTvShow.apply {
            adapter = tvshowAdapter
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
        binding.tvShowProgressBar.visibility = View.VISIBLE
        val response = tvshowViewModel.updateTvShows()
        response.observe(this, Observer {
            if(it!=null){
                tvshowAdapter.setList(it)
                tvshowAdapter.notifyDataSetChanged()
                binding.tvShowProgressBar.visibility = View.GONE
            }else{
                binding.tvShowProgressBar.visibility = View.GONE
            }
        })
    }

    companion object{
        private const val TAG = "MovieActivity"
    }
}