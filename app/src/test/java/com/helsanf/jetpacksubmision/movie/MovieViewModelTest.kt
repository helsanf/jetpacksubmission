package com.helsanf.jetpacksubmision.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.helsanf.jetpacksubmision.data.datasource.movie.RepositoryMovie
import com.helsanf.jetpacksubmision.model.Movie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import com.helsanf.jetpacksubmision.utils.FakeDataDummyTest
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
    private val movieList: List<ResultMovie>? = FakeDataDummyTest().generateMovie()
    private val movieMutableLiveData: MutableLiveData<List<ResultMovie>> = MutableLiveData()


    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        movieMutableLiveData.value = movieList
        Mockito.`when`(movieRepository.getMovieList()).thenReturn(movieMutableLiveData)
        val observer: Observer<*>? = Mockito.mock(Observer::class.java)
        viewModel.getAllMovie().observeForever(observer as Observer<in List<ResultMovie>>)
        Mockito.verify(observer).onChanged(movieList)
        assertNotNull(movieList)
        assertEquals(10,movieList?.size)
    }
}