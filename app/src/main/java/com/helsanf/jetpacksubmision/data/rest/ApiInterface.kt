package com.helsanf.jetpacksubmision.data.source.remote.rest

import com.helsanf.jetpacksubmision.model.modelrespone.movie.MovieResponses
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.TvShowResponses
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
     fun getMovieList(
        @Query("api_key") apiKey : String,
        @Query("language") bahasa : String,
        @Query("page") page : Int
    ) : Deferred<MovieResponses>
    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId : String,
        @Query("api_key") apiKey : String,
        @Query("language") bahasa : String
    ) : Deferred<ResultMovie>

    @GET("tv/popular")
    fun getAllTvShowPopuler(
        @Query("api_key") apiKey: String,
        @Query("language") bahasa : String,
        @Query("page") page : Int
    ) : Deferred<TvShowResponses>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tv_id : String,
        @Query("api_key") apiKey : String,
        @Query("language") bahasa : String
    ) : Deferred<ResultTvShow>

}