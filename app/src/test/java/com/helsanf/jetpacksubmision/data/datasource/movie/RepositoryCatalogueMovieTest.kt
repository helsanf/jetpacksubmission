package com.helsanf.jetpacksubmision.data.datasource.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.helsanf.jetpacksubmision.data.local.LocalRepository
import com.helsanf.jetpacksubmision.data.rest.remote.RemoteRepository
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import com.helsanf.jetpacksubmision.utils.FakeDataDummyTest
import com.helsanf.jetpacksubmision.utils.LiveDataUtils
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class RepositoryCatalogueMovieTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var remoteRepository = mock(RemoteRepository::class.java)
    private var localRepository = mock(LocalRepository::class.java)
    private val fakeRepo: FakeRepositoryMovie = FakeRepositoryMovie(remoteRepository,localRepository)
    private val movieList: List<ResultMovie>? = FakeDataDummyTest().generateMovie()
    private val tvList: List<ResultTvShow>? = FakeDataDummyTest().generateDummyTvShow()
    private val movieID = movieList?.get(0)?.id
    private val tvID = tvList?.get(0)?.id
    private val detailMovie = FakeDataDummyTest().getDetailMovie(movieID!!)
    private val detailTvShow = FakeDataDummyTest().getDetailTvShow(tvID!!)
    private fun <T> uninitialized(): T = null as T

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun getMovieList() {
        doAnswer {
            val callback = it.arguments[0] as RemoteRepository.MovieCallBack
            callback.movieLoadedSuccses(movieList!!)
            null
        }.`when`(remoteRepository).getAllMovie(anyObject())

        val result = LiveDataUtils().getValue(fakeRepo.getMovieList())
        verify(remoteRepository, times(1)).getAllMovie(anyObject())
        Assert.assertNotNull(result)
        Assert.assertEquals(movieList?.size, result.size)
    }

    @Test
    fun getDetailMovie() {
        doAnswer {
            val callback = it.arguments[1] as RemoteRepository.MovieDetailCallBack
            callback.detailLoaded(detailMovie!!)
            null
        }.`when`(remoteRepository).getDetailMovie(eq(movieID.toString()), anyObject())

        val result = LiveDataUtils().getValue(fakeRepo.getDetailMovie(movieID.toString()))
        verify(remoteRepository, times(1)).getDetailMovie(eq(movieID.toString()), anyObject())
        Assert.assertEquals(movieList?.get(0)?.title, result.title)

    }

    @Test
    fun getTvShowList(){
        doAnswer {
            val callback = it.arguments[0] as RemoteRepository.TvShowCallBack
            callback.tvShowLoadedSucces(tvList!!)
            null
        }.`when`(remoteRepository).getAllTvShow(anyObject())

        val result = LiveDataUtils().getValue(fakeRepo.getAllTvShow())
        verify(remoteRepository, times(1)).getAllTvShow(anyObject())
        Assert.assertNotNull(result)
        Assert.assertEquals(tvList!!.size, result.size)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer {
            val callback = it.arguments[1] as RemoteRepository.TvShowDetailCallBack
            callback.detailLoaded(detailTvShow!!)
            null
        }.`when`(remoteRepository).getDetailTvShow(eq(tvID.toString()), anyObject())

        val result = LiveDataUtils().getValue(fakeRepo.getDetailTvShow(tvID!!))
        verify(remoteRepository, times(1)).getDetailTvShow(eq(tvID.toString()), anyObject())
        Assert.assertEquals(tvList?.get(0)?.overview, result.overview)

    }

    private fun <T> anyObject(): T {
        Mockito.anyObject<T>()
        return uninitialized()
    }

}