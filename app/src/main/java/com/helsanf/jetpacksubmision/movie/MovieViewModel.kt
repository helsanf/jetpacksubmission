package com.helsanf.jetpacksubmision.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helsanf.jetpacksubmision.data.datasource.movie.RepositoryMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.utils.lazyDeferred
import kotlinx.coroutines.*

class MovieViewModel internal constructor(private val repository: RepositoryMovie) : ViewModel() {

    private val viewModelJob = Job()
    lateinit var uiScope: CoroutineScope
    var listMovie: MutableList<ResultMovie> = mutableListOf()
    //    private var listMutable : MutableLiveData
    private val listMutable: MutableLiveData<List<ResultMovie>> = MutableLiveData()

    //    private  var data = MutableLiveData<MovieResponses>().apply {
//value = MovieResponses(results = )
//    }
    val movieFromLocal = viewModelScope.async(Dispatchers.IO) {
       repository.getMovieFromFavorites()
    }

    private val movieFavoriteList = repository.getMovieFromFavorites()
    val favoritesMovie: LiveData<List<ResultMovie>>? get() = movieFavoriteList


    val movieFromApi = viewModelScope.async(Dispatchers.IO){
        repository.getMovieList()
    }

    fun insertAllAsync(movie : List<ResultMovie>) = GlobalScope.async{
        repository.insertAllMovie(movie)
    }
    //lazydeferred  berguna untuk mengisi ke movieList ketika data berhasil di ambil


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}