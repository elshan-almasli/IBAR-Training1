package com.anushka.tmdbclient.data.api

import com.anushka.tmdbclient.data.model.artist.ArtistList
import com.anushka.tmdbclient.data.model.movie.MovieList
import com.anushka.tmdbclient.data.model.tvshow.TVShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMBDService {

    @GET("movie/popular")
    suspend fun getMoviesList(@Query("api_key") api_key: String): Response<MovieList>

    @GET("tv/popular")
    suspend fun getTVShowList(@Query("api_key") api_key: String): Response<TVShowList>

    @GET("person/popular")
    suspend fun getPopularPeople(@Query("api_key") api_key: String): Response<ArtistList>

}