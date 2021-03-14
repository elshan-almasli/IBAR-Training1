package com.anushka.tmdbclient.presenter.artist

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
import com.anushka.tmdbclient.databinding.ActivityArtistBinding
import com.anushka.tmdbclient.presenter.di.Injector
import javax.inject.Inject

class ArtistActivity: AppCompatActivity() {
    private lateinit var binding: ActivityArtistBinding

    @Inject
    lateinit var factory: ArtistViewModelFactory

    private lateinit var artistViewModel: ArtistViewModel

    private lateinit var artistAdapter: ArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist)

        (application as Injector)
            .createArtistSubComponent()
            .inject(this)
        artistViewModel = ViewModelProvider(this,factory).get(ArtistViewModel::class.java)

        initRecyclerView()

        initObserver()


    }

    private fun initObserver() {
        artistViewModel.getArtists().observe(this, Observer { movie ->

            movie?.let {
//                Log.i(TAG, "initObserver: ${movie[0].overview}")
                artistAdapter.setList(it)
                artistAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun initRecyclerView(){
        artistAdapter = ArtistAdapter();
        binding.recyclerViewArtist.apply {
            adapter = artistAdapter
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
        binding.progressBarArtist.visibility = View.VISIBLE
        val response = artistViewModel.updateArtists()
        response.observe(this, Observer {
            if(it!=null){
                artistAdapter.setList(it)
                artistAdapter.notifyDataSetChanged()
                binding.progressBarArtist.visibility = View.GONE
            }else{
                binding.progressBarArtist.visibility = View.GONE
            }
        })
    }

    companion object{
        private const val TAG = "MovieActivity"
    }
}