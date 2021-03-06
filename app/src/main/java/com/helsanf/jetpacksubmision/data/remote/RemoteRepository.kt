package com.helsanf.jetpacksubmision.data.rest.remote

import android.util.Log
import androidx.annotation.NonNull
import com.helsanf.jetpacksubmision.BuildConfig
import com.helsanf.jetpacksubmision.data.source.remote.rest.ApiInterface
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import java.io.IOException

class RemoteRepository internal constructor(@NonNull val apiInterface: ApiInterface) {
//     private var idling = IdlingResource()

    suspend fun getAllMovie(movieCallback: MovieCallBack) {

        try {
            val fetchMovie = apiInterface.getMovieList(BuildConfig.API_KEY, "en-US", 1)
            if (fetchMovie.isSuccessful) {
                movieCallback.movieLoadedSuccses(fetchMovie.body()?.results ?: emptyList())
            } else {
                movieCallback.movieNotAvaliable("Some Error")
            }

        } catch (e: IOException) {
            Log.e("Error ","Connection")
        }

    }

    suspend fun getAllTvShow(tvShowCallback: TvShowCallBack?) {
        try {
            val fetchTvShow = apiInterface
                .getAllTvShowPopuler(BuildConfig.API_KEY, "en-US", 1)
            if (fetchTvShow.isSuccessful) {
                tvShowCallback?.tvShowLoadedSucces(fetchTvShow.body()?.results)
            } else {
                tvShowCallback?.tvShowNotLoaded("Some Error")
            }
        }catch (e : IOException){
            Log.e("Error ","Connection")

        }


//        fetchTvShow.results.apply {
//            if (this.isNotEmpty()) {
//                tvShowCallback?.tvShowLoadedSucces(this)
//            } else {
//                tvShowCallback?.tvShowNotLoaded("Error")
//            }
//        }
    }

    suspend fun getDetailMovie(id_movie: String?, movieDetailCallBack: MovieDetailCallBack?) {
        val detailMovieShow = apiInterface
            .getDetailMovie(id_movie!!, BuildConfig.API_KEY, "en-US").await()
        movieDetailCallBack?.detailLoaded(detailMovieShow)
    }

    suspend fun getDetailTvShow(id_tv: String?, tvShowDetailCallBack: TvShowDetailCallBack?) {
        val detailTvShow = apiInterface
            .getDetailTvShow(id_tv!!, BuildConfig.API_KEY, "en-US").await()
        tvShowDetailCallBack?.detailLoaded(detailTvShow)
    }

    interface MovieCallBack {
        fun movieLoadedSuccses(movie: List<ResultMovie>?)
        fun movieNotAvaliable(message: String)
    }

    interface TvShowCallBack {
        fun tvShowLoadedSucces(tvShow: List<ResultTvShow>?)
        fun tvShowNotLoaded(message: String)
    }

    interface MovieDetailCallBack {
        fun detailLoaded(movie: ResultMovie)
        fun detailErorLoaded(message: String)
    }

    interface TvShowDetailCallBack {
        fun detailLoaded(tvShow: ResultTvShow)
        fun detailErorLoaded(message: String)
    }
}