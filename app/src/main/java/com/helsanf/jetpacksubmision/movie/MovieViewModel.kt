package com.helsanf.jetpacksubmision.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.helsanf.jetpacksubmision.data.datasource.movie.RepositoryMovie
import com.helsanf.jetpacksubmision.model.Movie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.utils.DataDummy

class MovieViewModel internal constructor(private val repository: RepositoryMovie): ViewModel() {

    val movieList = repository.getMovieList()

//    fun getAllMovie() : LiveData<List<ResultMovie>>{
//        return repository.getMovieList()
//    }
}