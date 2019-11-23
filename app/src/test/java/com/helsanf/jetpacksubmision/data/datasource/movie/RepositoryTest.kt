package com.helsanf.jetpacksubmision.data.datasource.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.helsanf.jetpacksubmision.data.rest.remote.RemoteRepository
import com.helsanf.jetpacksubmision.data.source.remote.rest.ApiInterface
import com.helsanf.jetpacksubmision.data.source.remote.rest.ApiRepository
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.utils.FakeDataDummyTest
import com.helsanf.jetpacksubmision.utils.LiveDataUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var remoteRepository = mock(RemoteRepository::class.java)

    private val fakeRepo: FakeRepositoryMovie = FakeRepositoryMovie(remoteRepository)

    private val movieList : List<ResultMovie> = FakeDataDummyTest().generateMovie()
    private val detailMovie = FakeDataDummyTest().getDetailMovie(movieList[0].id.toString())

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

//        remoteRepository = RemoteRepository(ApiRepository().getUrl().create(ApiInterface::class.java))
    }

    @Test
    fun getMovieList() {
        Mockito.doAnswer {
            val movieCallback: RemoteRepository.MovieCallBack = it.arguments[0] as RemoteRepository.MovieCallBack
            movieCallback.movieLoadedSuccses(movieList)
            it
        }.`when`(remoteRepository).getAllMovie(Mockito.any(RemoteRepository.MovieCallBack::class.java))

        val result: List<ResultMovie> = LiveDataUtils().getValue(fakeRepo.getMovieList())
        Mockito.verify(remoteRepository, Mockito.times(1))
            .getAllMovie(Mockito.any(RemoteRepository.MovieCallBack::class.java))
        Assert.assertEquals(movieList.size, result.size)



    }

}