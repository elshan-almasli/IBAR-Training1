package com.anushka.tmdbclient.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.anushka.tmdbclient.R
import com.anushka.tmdbclient.databinding.ActivityMainBinding
import com.anushka.tmdbclient.presenter.artist.ArtistActivity
import com.anushka.tmdbclient.presenter.movie.MovieActivity
import com.anushka.tmdbclient.presenter.tvshow.TvShowActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.buttonMovie.setOnClickListener {
            Intent(this,MovieActivity::class.java).apply {
                startActivity(this)
            }
        }

        binding.buttonArtist.setOnClickListener {
            Intent(this, ArtistActivity::class.java).apply {
                startActivity(this)
            }
        }

        binding.buttonTvShow.setOnClickListener {
            Intent(this, TvShowActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}