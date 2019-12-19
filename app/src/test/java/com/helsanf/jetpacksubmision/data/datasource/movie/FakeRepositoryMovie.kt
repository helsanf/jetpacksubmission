package com.helsanf.jetpacksubmision.data.datasource.movie


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.helsanf.jetpacksubmision.data.local.LocalRepository
import com.helsanf.jetpacksubmision.data.rest.remote.RemoteRepository
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow


class FakeRepositoryMovie constructor(var remoteRepository: RemoteRepository, private val localRepository: LocalRepository) :
    MovieDataSource {
    @Volatile
    private var INSTANCE: RepositoryMovie? = null

    fun getInstance(remoteRepository: RemoteRepository): RepositoryMovie {
        if (INSTANCE == null) {
            synchronized(RepositoryMovie::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = RepositoryMovie(
                        remoteRepository,localRepository
                    )
                }
            }
        }
        return INSTANCE!!
    }

    override suspend fun getMovieList(): LiveData<List<ResultMovie>> {
        val listMovie: MutableLiveData<List<ResultMovie>> = MutableLiveData()
        remoteRepository.getAllMovie(object :
            RemoteRepository.MovieCallBack {
            override fun movieLoadedSuccses(movie: List<ResultMovie>) {
                listMovie.postValue(movie)
            }

            override fun movieNotAvaliable(message: String) {

            }

        })
        return listMovie

    }

    override suspend fun getDetailMovie(id_movie: String?): LiveData<ResultMovie> {
        val movieDetail: MutableLiveData<ResultMovie> = MutableLiveData()
        remoteRepository.getDetailMovie(id_movie, object : RemoteRepository.MovieDetailCallBack {
            override fun detailLoaded(movie: ResultMovie) {
                movieDetail.postValue(movie)
            }

            override fun detailErorLoaded(message: String) {

            }

        })
        return movieDetail
    }

    override suspend fun getAllTvShow(): LiveData<List<ResultTvShow>> {
        val listTvShow: MutableLiveData<List<ResultTvShow>> = MutableLiveData()
        remoteRepository.getAllTvShow(object : RemoteRepository.TvShowCallBack {
            override fun tvShowLoadedSucces(tvShow: List<ResultTvShow>) {
                listTvShow.postValue(tvShow)
            }

            override fun tvShowNotLoaded(message: String) {

            }

        })
        return listTvShow
    }

    override suspend fun getDetailTvShow(id_tv: Int?): LiveData<ResultTvShow> {
        val tvDetail: MutableLiveData<ResultTvShow> = MutableLiveData()
        remoteRepository.getDetailTvShow(id_tv.toString(), object : RemoteRepository.TvShowDetailCallBack {
            override fun detailLoaded(tvShow: ResultTvShow) {
                tvDetail.postValue(tvShow)
            }

            override fun detailErorLoaded(message: String) {

            }

        })
        return tvDetail
    }

    override fun getMovieFromFavorites(): DataSource.Factory<Int, ResultMovie>? {
        return localRepository.getMovieFavorites()
    }

    override fun getTvShowFromFavorites(): DataSource.Factory<Int, ResultTvShow>? {
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


}