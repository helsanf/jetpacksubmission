package com.helsanf.jetpacksubmision.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.helsanf.jetpacksubmision.data.datasource.movie.RepositoryMovie
import com.helsanf.jetpacksubmision.model.TvShow
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import com.helsanf.jetpacksubmision.utils.FakeDataDummyTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TvShowViewModelTest{
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: TvShowViewModel
    var movieRepository = Mockito.mock(RepositoryMovie::class.java)

    private val tvList: List<ResultTvShow>? = FakeDataDummyTest().generateDummyTvShow()
    private val tvMutableLiveData: MutableLiveData<List<ResultTvShow>> = MutableLiveData()

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getTvShow(){
        tvMutableLiveData.value = tvList
        Mockito.`when`(movieRepository.getAllTvShow()).thenReturn(tvMutableLiveData)
        val observer: Observer<*>? = Mockito.mock(Observer::class.java)
        viewModel.tvShow.observeForever(observer as Observer<in List<ResultTvShow>>)
        Mockito.verify(observer).onChanged(tvList)
        assertNotNull(tvList)
        assertEquals(10,tvList?.size)
    }
}