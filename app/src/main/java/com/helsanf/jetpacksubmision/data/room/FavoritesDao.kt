package com.helsanf.jetpacksubmision.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie : ResultMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovie(movie: List<ResultMovie>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow : ResultTvShow)

    @Delete
    fun deleteMovie(movie: ResultMovie)

    @Delete
    fun deleteTvShow(tvShow : ResultTvShow)

    @Query("SELECT * FROM table_movie")
    fun getAllMovieLocal() : LiveData<List<ResultMovie>>

    @Query("SELECT * FROM table_tvshow")
    fun getAllTvShowLocal() : LiveData<List<ResultTvShow>>

    @Query("SELECT * FROM table_movie WHERE id = :id_movie")
    fun getDetailMovie(id_movie: String?) : LiveData<ResultMovie>

    @Query("SELECT * FROM table_tvshow WHERE id = :id_tv")
    fun getDetailTvShow(id_tv: Int?) : LiveData<ResultTvShow>
}