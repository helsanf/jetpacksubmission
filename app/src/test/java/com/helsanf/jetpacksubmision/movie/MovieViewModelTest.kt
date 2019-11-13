package com.helsanf.jetpacksubmision.movie

import com.helsanf.jetpacksubmision.model.Movie
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovie(){
        val movie : List<Movie> = viewModel.getMovie()
        assertNotNull(movie)
        assertEquals(10,movie.size)
    }
}