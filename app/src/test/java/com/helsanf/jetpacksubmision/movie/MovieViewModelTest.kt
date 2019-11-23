package com.helsanf.jetpacksubmision.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.helsanf.jetpacksubmision.data.datasource.movie.RepositoryMovie
import com.helsanf.jetpacksubmision.model.Movie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    var movieRepository = Mockito.mock(RepositoryMovie::class.java)
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovie(){
        val movie : LiveData<List<ResultMovie>> = viewModel.getAllMovie()
        Mockito.`when`(movieRepository.getMovieList()).thenReturn(movie)

        assertNotNull(movie)
        assertEquals(20,movie.value?.size)
    }
}