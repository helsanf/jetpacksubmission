package com.helsanf.jetpacksubmision.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.helsanf.jetpacksubmision.data.datasource.movie.RepositoryMovie
import com.helsanf.jetpacksubmision.model.Movie
import com.helsanf.jetpacksubmision.model.TvShow
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import com.helsanf.jetpacksubmision.utils.DataDummy

class DetailViewModel internal constructor(private val repository: RepositoryMovie) : ViewModel() {
    private var idMovie: String? = null
    private var idTv : Int? = null

    fun getDetailMovie() : LiveData<ResultMovie>{
        return repository.getDetailMovie(getMovieId())
    }
    fun getDetailTvShow() : LiveData<ResultTvShow>{
        return repository.getDetailTvShow(getTvId())
    }


    fun setMovieId(movieId : String){
        this.idMovie = movieId
    }
    fun getMovieId() : String{
        return idMovie!!
    }

    fun setTvId(idTv : Int){
        this.idTv = idTv
    }
    fun getTvId() : Int{
        return idTv!!
    }

}