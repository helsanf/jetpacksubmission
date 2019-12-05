package com.helsanf.jetpacksubmision.data.rest.remote

import androidx.annotation.NonNull
import androidx.paging.PageKeyedDataSource
import com.helsanf.jetpacksubmision.BuildConfig
import com.helsanf.jetpacksubmision.data.source.remote.rest.ApiInterface
import com.helsanf.jetpacksubmision.model.modelrespone.movie.MovieResponses
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.TvShowResponses
import com.helsanf.jetpacksubmision.utils.IdlingResource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

 class RemoteRepository internal constructor(@NonNull val apiInterface: ApiInterface)  {
//     private var idling = IdlingResource()

     fun getAllMovie(movieCallBack: MovieCallBack?)  {
       IdlingResource.increment()
        val connect = apiInterface.getMovieList(BuildConfig.API_KEY, "en-US", 1)
         connect.subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(object : Observer<MovieResponses>{
                 override fun onNext(t: MovieResponses?) {
                     if (t != null) {
                         movieCallBack?.movieLoadedSuccses(t.results)
                         IdlingResource.decrement()
                     }
                 }

                 override fun onComplete() {

                 }

                 override fun onSubscribe(d: Disposable?) {

                 }

                 override fun onError(e: Throwable?) {
                     movieCallBack?.movieNotAvaliable(e?.message.toString())
                 }

             })
    }

    fun getAllTvShow(tvShowCallback: TvShowCallBack?){
       IdlingResource.increment()
        val connect = apiInterface.getAllTvShowPopuler(BuildConfig.API_KEY,"en-US", 1)
        connect.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<TvShowResponses>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(t: TvShowResponses?) {
                    if(t != null){
                        tvShowCallback?.tvShowLoadedSucces(t.results)
                       IdlingResource.decrement()

                    }
                }

                override fun onError(e: Throwable?) {
                    tvShowCallback?.tvShowNotLoaded(e?.message.toString())
                }

            })
    }
    fun getDetailMovie(id_movie : String?,movieDetailCallBack: MovieDetailCallBack?){
        IdlingResource.increment()
        val connect = apiInterface.getDetailMovie(id_movie!!,BuildConfig.API_KEY,"en-US")
        connect.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResultMovie>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(t: ResultMovie?) {
                    if (t != null) {
                        movieDetailCallBack?.detailLoaded(t)
                        IdlingResource.decrement()
                    }
                }

                override fun onError(e: Throwable?) {
                   movieDetailCallBack?.detailErorLoaded(e?.message.toString())
                }

            })
    }
    fun getDetailTvShow(id_tv : String?,tvShowDetailCallBack: TvShowDetailCallBack?){
        IdlingResource.increment()
        val connect  = apiInterface.getDetailTvShow(id_tv!!,BuildConfig.API_KEY,"en-US")
         connect.subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(object : Observer<ResultTvShow>{
                 override fun onComplete() {

                 }

                 override fun onSubscribe(d: Disposable?) {

                 }

                 override fun onNext(t: ResultTvShow?) {
                     if (t != null) {
                         tvShowDetailCallBack?.detailLoaded(t)
                         IdlingResource.decrement()
                     }
                 }

                 override fun onError(e: Throwable?) {
                    tvShowDetailCallBack?.detailErorLoaded(e?.message.toString())
                 }

             })
    }

    interface MovieCallBack{
        fun movieLoadedSuccses(movie : List<ResultMovie>)
        fun movieNotAvaliable(message : String)
    }
    interface TvShowCallBack{
        fun tvShowLoadedSucces(tvShow : List<ResultTvShow>)
        fun tvShowNotLoaded(message : String)
    }
    interface MovieDetailCallBack{
        fun detailLoaded(movie : ResultMovie)
        fun detailErorLoaded(message : String)
    }
    interface TvShowDetailCallBack{
        fun detailLoaded(tvShow : ResultTvShow)
        fun detailErorLoaded(message : String)
    }
}