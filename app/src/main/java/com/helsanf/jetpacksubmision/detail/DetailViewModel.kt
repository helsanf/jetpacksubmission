package com.helsanf.jetpacksubmision.detail

import androidx.lifecycle.ViewModel
import com.helsanf.jetpacksubmision.model.Movie
import com.helsanf.jetpacksubmision.model.TvShow
import com.helsanf.jetpacksubmision.utils.DataDummy

class DetailViewModel : ViewModel() {
    private var idMovie: String? = null
    private var movie: Movie? = null
    private var idTv : String? = null
    private var tvShow: TvShow? = null

    fun getMovie(): Movie? {
        for (i in 0 until DataDummy().generateMovie().size) {
            val movieDetail = DataDummy().generateMovie().get(i)
            if (movieDetail.movieId.equals(getMovieId())) {
                movie = movieDetail
            }
        }
        return movie
    }
    fun getTvShow(): TvShow? {
        for (i in 0 until DataDummy().generateDummyTvShow().size) {
            val tvShowDetail = DataDummy().generateDummyTvShow().get(i)
            if (tvShowDetail.idTvShow.equals(getTvId())) {
                tvShow = tvShowDetail
            }
        }
        return tvShow
    }

    fun setMovieId(movieId : String){
        this.idMovie = movieId
    }
    fun getMovieId() : String{
        return idMovie!!
    }

    fun setTvId(idTv : String){
        this.idTv = idTv
    }
    fun getTvId() : String{
        return idTv!!
    }

}