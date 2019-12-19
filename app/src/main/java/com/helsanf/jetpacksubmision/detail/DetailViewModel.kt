package com.helsanf.jetpacksubmision.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.helsanf.jetpacksubmision.data.datasource.movie.RepositoryMovie
import com.helsanf.jetpacksubmision.model.Movie
import com.helsanf.jetpacksubmision.model.TvShow
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import com.helsanf.jetpacksubmision.utils.DataDummy
import com.helsanf.jetpacksubmision.utils.lazyDeferred

class DetailViewModel internal constructor(private val repository: RepositoryMovie) : ViewModel() {
    private var idMovie: String? = null
    private var idTv: Int? = null
    val getDetailMovie by lazyDeferred {
        repository.getDetailMovie(getMovieId())
    }

    val getDetailTvShow by lazyDeferred {
        repository.getDetailTvShow(getTvId())
    }

//    fun getDetailMovie(): LiveData<ResultMovie> {
//        return repository.getDetailMovie(getMovieId())
//    }

//    fun getDetailTvShow(): LiveData<ResultTvShow> {
//        return repository.getDetailTvShow(getTvId())
//    }

    fun addToFavoritesMovie(movie: ResultMovie) {
        return repository.addToFavoriteMovie(movie)
    }
    fun addtoFavoritesTvShow(tvShow : ResultTvShow){
        return repository.addToFavoriteTvShow(tvShow)
    }
    fun getDetailMovieFromFavorites() : LiveData<ResultMovie>? {
        return repository.getDetailMovieFromFavorites(getMovieId())
    }

    fun getDetailTvShowFromFavorites() : LiveData<ResultTvShow>?{
        return repository.getDetailTvShowFromFavorites(getTvId())
    }
    fun unFavoritesMovie(movie: ResultMovie){
        return repository.unFavoritesMovie(movie)
    }
    fun unFavoritesTvShow(tvShow: ResultTvShow){
        return repository.unFavoritesTvShow(tvShow)
    }


    fun setMovieId(movieId: String) {
        this.idMovie = movieId
    }

    fun getMovieId(): String {
        return idMovie!!
    }

    fun setTvId(idTv: Int) {
        this.idTv = idTv
    }

    fun getTvId(): Int {
        return idTv!!
    }

}