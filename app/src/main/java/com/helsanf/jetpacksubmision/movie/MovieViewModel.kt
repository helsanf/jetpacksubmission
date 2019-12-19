package com.helsanf.jetpacksubmision.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.helsanf.jetpacksubmision.data.datasource.movie.RepositoryMovie
import com.helsanf.jetpacksubmision.model.Movie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.utils.DataDummy
import com.helsanf.jetpacksubmision.utils.lazyDeferred
import kotlinx.coroutines.*

class MovieViewModel internal constructor(private val repository: RepositoryMovie) : ViewModel() {

    private val viewModelJob = Job()
    lateinit var uiScope: CoroutineScope

//    val movieList = GlobalScope.launch(Dispatchers.Main + viewModelJob){
//        repository.getMovieList()
//    }

    val movieList by lazyDeferred {
        repository.getMovieList() }
    //lazydeferred  berguna untuk mengisi ke movieList ketika data berhasil di ambil

    private val movieFavoriteList = repository.getMovieFromFavorites()
    private val liveDataMovie = movieFavoriteList?.let { LivePagedListBuilder(it, 10).build() }

    val favoritesMovie: LiveData<PagedList<ResultMovie>>? get() = liveDataMovie

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}