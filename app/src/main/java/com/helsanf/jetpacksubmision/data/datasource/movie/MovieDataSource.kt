package com.helsanf.jetpacksubmision.data.datasource.movie

import androidx.lifecycle.LiveData
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow

interface MovieDataSource {
    fun getMovieList() : LiveData<List<ResultMovie>>
    fun getDetailMovie(id_movie : String?) : LiveData<ResultMovie>
    fun getAllTvShow() : LiveData<List<ResultTvShow>>
    fun getDetailTvShow(id_tv : Int?) : LiveData<ResultTvShow>
}