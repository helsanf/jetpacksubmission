package com.helsanf.jetpacksubmision.data.rest.remote

import androidx.annotation.NonNull
import androidx.paging.PageKeyedDataSource
import com.helsanf.jetpacksubmision.BuildConfig
import com.helsanf.jetpacksubmision.data.source.remote.rest.ApiInterface
import com.helsanf.jetpacksubmision.data.source.remote.rest.ApiRepository
import com.helsanf.jetpacksubmision.model.modelrespone.movie.MovieResponses
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.TvShowResponses
import com.helsanf.jetpacksubmision.utils.IdlingResource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RemoteRepository internal constructor(@NonNull val apiInterface: ApiInterface) {
//     private var idling = IdlingResource()

    suspend fun getAllMovie(movieCallback: MovieCallBack) {
        val fetchMovie = apiInterface
            .getMovieList(BuildConfig.API_KEY, "en-US", 1).await()
        fetchMovie.results.apply {
            if (this.isNotEmpty()) {
                movieCallback.movieLoadedSuccses(this)

            } else {
                movieCallback.movieNotAvaliable("Error")
            }
        }
    }

    suspend fun getAllTvShow(tvShowCallback: TvShowCallBack?) {
        val fetchTvShow = apiInterface
            .getAllTvShowPopuler(BuildConfig.API_KEY, "en-US", 1).await()
        fetchTvShow.results.apply {
            if (this.isNotEmpty()) {
                tvShowCallback?.tvShowLoadedSucces(this)
            } else {
                tvShowCallback?.tvShowNotLoaded("Error")
            }
        }
    }

    suspend fun getDetailMovie(id_movie: String?, movieDetailCallBack: MovieDetailCallBack?) {
        val detailMovieShow = apiInterface
            .getDetailMovie(id_movie!!, BuildConfig.API_KEY, "en-US").await()
        movieDetailCallBack?.detailLoaded(detailMovieShow)
    }

    suspend fun getDetailTvShow(id_tv: String?, tvShowDetailCallBack: TvShowDetailCallBack?){
        val detailTvShow = apiInterface
            .getDetailTvShow(id_tv!!, BuildConfig.API_KEY, "en-US").await()
        tvShowDetailCallBack?.detailLoaded(detailTvShow)
    }

    interface MovieCallBack {
        fun movieLoadedSuccses(movie: List<ResultMovie>)
        fun movieNotAvaliable(message: String)
    }

    interface TvShowCallBack {
        fun tvShowLoadedSucces(tvShow: List<ResultTvShow>)
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