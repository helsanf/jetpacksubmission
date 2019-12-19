package com.helsanf.jetpacksubmision.data.datasource.movie

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow

interface MovieDataSource {
    suspend fun getMovieList() : LiveData<List<ResultMovie>>
    suspend fun getDetailMovie(id_movie : String?) : LiveData<ResultMovie>
    suspend fun getAllTvShow() : LiveData<List<ResultTvShow>>
    suspend fun getDetailTvShow(id_tv : Int?) : LiveData<ResultTvShow>
    fun getMovieFromFavorites() : DataSource.Factory<Int, ResultMovie>?
    fun getTvShowFromFavorites() : DataSource.Factory<Int, ResultTvShow>?
    fun addToFavoriteMovie(movie: ResultMovie)
    fun addToFavoriteTvShow(tvShow: ResultTvShow)
    fun unFavoritesMovie(movie: ResultMovie)
    fun unFavoritesTvShow(tvShow: ResultTvShow)
    fun getDetailMovieFromFavorites(id_movie : String?) : LiveData<ResultMovie>?
    fun getDetailTvShowFromFavorites(id_tv : Int?) : LiveData<ResultTvShow>?


}