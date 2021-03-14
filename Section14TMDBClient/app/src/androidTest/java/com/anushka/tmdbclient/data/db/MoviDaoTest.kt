package com.anushka.tmdbclient.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anushka.tmdbclient.data.model.movie.Movie
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieDao: MovieDao
    private lateinit var tmdbDatabase: TMDBDatabase

    @Before
    fun setUp() {
        tmdbDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TMDBDatabase::class.java
        ).build()
        movieDao = tmdbDatabase.movieDao()
    }

    @After
    fun tearDown() {
        tmdbDatabase.close()
    }

    @Test
    fun saveMovieTest() = runBlocking {
        val list = listOf(
            Movie(1, "overview1", 1.2, "poster_path1",
                "release_data1", "title1"),
            Movie(2, "overview2", 1.2, "poster_path2",
                "release_data2", "title2"),
            Movie(3, "overview3", 1.2, "poster_path3",
                "release_data3", "title3")
        )

        movieDao.insertMovie(list)

        val movieList = movieDao.selectAllMovies()

        Truth.assertThat(movieList).isEqualTo(list)
    }

    @Test
    fun deleteMovieTest() = runBlocking {
        val list = listOf(
            Movie(1, "overview1", 1.2, "poster_path1",
                "release_data1", "title1"),
            Movie(2, "overview2", 1.2, "poster_path2",
                "release_data2", "title2"),
            Movie(3, "overview3", 1.2, "poster_path3",
                "release_data3", "title3")
        )

        movieDao.insertMovie(list)
        movieDao.deleteAllMovies()

        val movieList = movieDao.selectAllMovies()

        Truth.assertThat(movieList).isEmpty()
    }


}