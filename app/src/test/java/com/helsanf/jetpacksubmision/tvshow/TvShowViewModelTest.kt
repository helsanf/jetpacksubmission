package com.helsanf.jetpacksubmision.tvshow

import com.helsanf.jetpacksubmision.model.TvShow
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest{
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow(){
        val tvShow : List<TvShow> = viewModel.getTvShow()
        assertNotNull(tvShow)
        assertEquals(10,tvShow.size)
    }
}