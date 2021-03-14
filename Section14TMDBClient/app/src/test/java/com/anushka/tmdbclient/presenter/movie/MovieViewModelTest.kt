package com.anushka.tmdbclient.presenter.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.domain.repository.FakeRepository
import com.anushka.tmdbclient.domain.usecase.movie.GetMovieUseCase
import com.anushka.tmdbclient.domain.usecase.movie.UpdateMovieUseCase
import com.anushka.tmdbclient.getOrAwaitValue
import com.google.common.truth.Truth
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp() {
        val fakeRepository = FakeRepository()
        val getMovieUseCase = GetMovieUseCase(fakeRepository)
        val updateMovieUseCase = UpdateMovieUseCase(fakeRepository)

        movieViewModel = MovieViewModel(getMovieUseCase,updateMovieUseCase)
    }

    @Test
    fun getMovies_returnCurrentItemsTest(){
        val movieList = mutableListOf<Movie>()
        movieList.add(Movie(1, "overview1", 1.2, "poster_path1",
            "release_data1", "title1"))
        movieList.add(Movie(2, "overview2", 1.2, "poster_path2",
            "release_data2", "title2"))

        val currentList = movieViewModel.getMovie().getOrAwaitValue()
        Truth.assertThat(currentList).isEqualTo(movieList)

    }

    @Test
    fun updateMovies_returnCurrentItemsTest(){
        val list = mutableListOf<Movie>()
        list.add(Movie(3, "overview3", 1.2, "poster_path3",
            "release_data3", "title3"))
        list.add(Movie(4, "overview4", 1.2, "poster_path4",
            "release_data4", "title4"))

        val currentList = movieViewModel.updateMovie().getOrAwaitValue()
        Truth.assertThat(currentList).isEqualTo(list)

    }
}