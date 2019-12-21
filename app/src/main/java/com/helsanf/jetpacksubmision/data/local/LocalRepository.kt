package com.helsanf.jetpacksubmision.data.local

import androidx.lifecycle.LiveData
import com.helsanf.jetpacksubmision.data.room.FavoritesDao
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import java.util.concurrent.Executor

class LocalRepository(private val favoritesDao: FavoritesDao? ,  private val ioExecutor: Executor) {

    fun getMovieFavorites(): LiveData<List<ResultMovie>>? {
        return favoritesDao?.getAllMovieLocal()
    }

    fun getTvShowFavorites(): LiveData<List<ResultTvShow>>? {
        return favoritesDao?.getAllTvShowLocal()
    }

    fun insertMovieToFavorites(movie: ResultMovie) {
        ioExecutor.execute {
            favoritesDao?.insertMovie(movie)
        }
    }
    fun insertTvShowToFavorites(tvShow : ResultTvShow){
        ioExecutor.execute {
            favoritesDao?.insertTvShow(tvShow)

        }
    }
    fun getDetailMovieFavorites(id_movie: String?) : LiveData<ResultMovie>? {
        return favoritesDao?.getDetailMovie(id_movie)
    }
    fun getDetailTvShowFavorites(id_tv: Int?) : LiveData<ResultTvShow>? {
        return favoritesDao?.getDetailTvShow(id_tv)
    }
    fun deleteMovie(movie: ResultMovie){
      ioExecutor.execute {
          favoritesDao?.deleteMovie(movie)
      }
    }
    fun deleteTvShow(tvShow : ResultTvShow){
        ioExecutor.execute {
            favoritesDao?.deleteTvShow(tvShow)
        }
    }
    fun insertAllMovie(movie: List<ResultMovie>?){
        favoritesDao?.insertAllMovie(movie)

//        ioExecutor.execute {
//        }
    }

}