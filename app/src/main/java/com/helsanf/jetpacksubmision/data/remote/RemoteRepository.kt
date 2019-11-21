package com.helsanf.jetpacksubmision.data.rest.remote

import com.helsanf.jetpacksubmision.BuildConfig
import com.helsanf.jetpacksubmision.data.source.remote.rest.ApiInterface
import com.helsanf.jetpacksubmision.model.modelrespone.movie.MovieResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository(private val apiInterface: ApiInterface) {

    private fun getAllMovie() {
        val connect = apiInterface.getMovieList(BuildConfig.API_KEY, "en-US", 1)
        connect.enqueue(object : Callback<MovieResponses> {
            override fun onFailure(call: Call<MovieResponses>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<MovieResponses>,
                response: Response<MovieResponses>
            ) {
                val get = response.body()?.results

            }

        })
    }
}