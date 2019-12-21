package com.helsanf.jetpacksubmision.data.datasource.movie

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.helsanf.jetpacksubmision.data.local.LocalRepository
import com.helsanf.jetpacksubmision.data.rest.remote.RemoteRepository
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryMovie constructor(private val remoteRepository: RemoteRepository
                                  , private val localRepository: LocalRepository,
                                  private val context: Context) :
    MovieDataSource {


    fun getInstance(
        remoteRepository: RemoteRepository,
        localRepository: LocalRepository
    ): RepositoryMovie {
        if (INSTANCE == null) {
            synchronized(RepositoryMovie::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = RepositoryMovie(
                        remoteRepository, localRepository,context
                    )
                }
            }
        }
        return INSTANCE!!
    }

    @Volatile
    private var INSTANCE: RepositoryMovie? = null

//    private var idling = IdlingResource()

    override suspend fun getMovieList(): LiveData<List<ResultMovie>> {
//        IdlingResource.increment()
        return withContext(Dispatchers.IO) {
            val listMovie: MutableLiveData<List<ResultMovie>> = MutableLiveData()
            remoteRepository.getAllMovie(object :
                RemoteRepository.MovieCallBack {
                override fun movieLoadedSuccses(movie: List<ResultMovie>?) {
                    listMovie.postValue(movie)
//                IdlingResource.decrement()
                }

                override fun movieNotAvaliable(message: String) {
                    showMessage(message)
                }

            })
            return@withContext listMovie
        }


    }

    override suspend fun getAllTvShow(): LiveData<List<ResultTvShow>> {
//        IdlingResource.increment()
        return withContext(Dispatchers.IO) {
            val listTvShow: MutableLiveData<List<ResultTvShow>> = MutableLiveData()
            remoteRepository.getAllTvShow(object : RemoteRepository.TvShowCallBack {
                override fun tvShowLoadedSucces(tvShow: List<ResultTvShow>?) {
                    listTvShow.postValue(tvShow)
//                    IdlingResource.decrement()
                }

                override fun tvShowNotLoaded(message: String) {

                }

            })
            return@withContext listTvShow
        }

    }

    override suspend fun getDetailMovie(id_movie: String?): LiveData<ResultMovie> {
        return withContext(Dispatchers.IO) {
            val movieDetail: MutableLiveData<ResultMovie> = MutableLiveData()
            remoteRepository.getDetailMovie(
                id_movie,
                object : RemoteRepository.MovieDetailCallBack {
                    override fun detailLoaded(movie: ResultMovie) {
                        movieDetail.postValue(movie)
                    }

                    override fun detailErorLoaded(message: String) {

                    }

                })
            return@withContext movieDetail
        }


    }

    override suspend fun getDetailTvShow(id_tv: Int?): LiveData<ResultTvShow> {
        val tvDetail: MutableLiveData<ResultTvShow> = MutableLiveData()
        remoteRepository.getDetailTvShow(id_tv.toString(),
            object : RemoteRepository.TvShowDetailCallBack {
                override fun detailLoaded(tvShow: ResultTvShow) {
                    tvDetail.postValue(tvShow)

                }

                override fun detailErorLoaded(message: String) {

                }

            })
        return tvDetail
    }

    override fun getMovieFromFavorites(): LiveData<List<ResultMovie>>? {
        return localRepository.getMovieFavorites()
    }

    override fun getTvShowFromFavorites(): LiveData<List<ResultTvShow>>? {
        return localRepository.getTvShowFavorites()
    }

    override fun addToFavoriteMovie(movie: ResultMovie) {
        return localRepository.insertMovieToFavorites(movie)
    }

    override fun addToFavoriteTvShow(tvShow: ResultTvShow) {
        return localRepository.insertTvShowToFavorites(tvShow)
    }

    override fun unFavoritesMovie(movie: ResultMovie) {
        return localRepository.deleteMovie(movie)
    }

    override fun unFavoritesTvShow(tvShow: ResultTvShow) {
        return localRepository.deleteTvShow(tvShow)
    }

    override fun getDetailMovieFromFavorites(id_movie: String?): LiveData<ResultMovie>? {
        return localRepository.getDetailMovieFavorites(id_movie)
    }

    override fun getDetailTvShowFromFavorites(id_tv: Int?): LiveData<ResultTvShow>? {
        return localRepository.getDetailTvShowFavorites(id_tv)
    }

    override suspend fun insertAllMovie(movie: List<ResultMovie>?) {
        return localRepository.insertAllMovie(movie)
    }

    fun showMessage(message : String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

}