package com.anushka.tmdbclient.domain.repository

import com.anushka.tmdbclient.data.model.movie.Movie

class FakeRepository: IMovieRepository {

    private val list = mutableListOf<Movie>()

    init {
        list.addAll(listOf(
            Movie(1, "overview1", 1.2, "poster_path1",
                "release_data1", "title1"),
            Movie(2, "overview2", 1.2, "poster_path2",
                "release_data2", "title2")
        )
        )
    }

    override suspend fun getMovie(): List<Movie>? {
        return list
    }

    override suspend fun updateMovie(): List<Movie>? {
        list.clear()
        list.add(Movie(3, "overview3", 1.2, "poster_path3",
                "release_data3", "title3"))
        list.add(Movie(4, "overview4", 1.2, "poster_path4",
            "release_data4", "title4"))
        return list
    }
}