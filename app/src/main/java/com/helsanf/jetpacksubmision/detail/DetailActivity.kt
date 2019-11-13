package com.helsanf.jetpacksubmision.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.helsanf.jetpacksubmision.R
import com.helsanf.jetpacksubmision.model.Movie
import com.helsanf.jetpacksubmision.model.TvShow
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private var viewModel : DetailViewModel? = null
    private var movieId: String? = null
    private var tvShowId : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        val extras: Bundle? = intent.extras
        movieId = extras?.getString("movie_id")
        tvShowId = extras?.getString("tv_id")

        if(movieId != null){
            viewModel?.setMovieId(movieId!!)
            getDetailMovie(viewModel?.getMovie()!!)
        }else if(tvShowId != null){
            viewModel?.setTvId(tvShowId!!)
            getDetailTvShow(viewModel?.getTvShow()!!)
        }

    }

    fun getDetailMovie(movie : Movie){
        tvTitle.text = movie.title
        tv_release_date.text = movie.release_date
        tv_rating.text = movie.vote_overage
        tv_overview.text = movie.overview
        movie.poster_path?.let { Picasso.get().load(it).into(imgDetail) }
    }

    fun getDetailTvShow(tvShow: TvShow){
        tvTitle.text = tvShow.title
        tv_release_date.text = tvShow.release_date
        tv_rating.text = tvShow.vote_average
        tv_overview.text = tvShow.overview
        tvShow.poster_path?.let { Picasso.get().load(it).into(imgDetail) }
    }
}
