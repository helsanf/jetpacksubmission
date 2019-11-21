package com.helsanf.jetpacksubmision.data.source.remote.rest

import com.helsanf.jetpacksubmision.model.modelrespone.movie.MovieResponses
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
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
    ) : Call<MovieResponses>
    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId : String,
        @Query("api_key") apiKey : String,
        @Query("language") bahasa : String
    ) : Call<ResultMovie>

}