package com.helsanf.jetpacksubmision.data.datasource.movie


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.helsanf.jetpacksubmision.data.rest.remote.RemoteRepository
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow


class FakeRepositoryMovie constructor(var remoteRepository: RemoteRepository) :
    MovieDataSource {
    @Volatile
    private var INSTANCE: RepositoryMovie? = null

    fun getInstance(remoteRepository: RemoteRepository): RepositoryMovie {
        if (INSTANCE == null) {
            synchronized(RepositoryMovie::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = RepositoryMovie(
                        remoteRepository
                    )
                }
            }
        }
        return INSTANCE!!
    }

    override fun getMovieList(): LiveData<List<ResultMovie>> {
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

    override fun getDetailMovie(id_movie: String?): LiveData<ResultMovie> {
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

    override fun getAllTvShow(): LiveData<List<ResultTvShow>> {
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

    override fun getDetailTvShow(id_tv: Int?): LiveData<ResultTvShow> {
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


}