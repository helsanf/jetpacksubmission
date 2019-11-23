package com.helsanf.jetpacksubmision.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.helsanf.jetpacksubmision.R
import com.helsanf.jetpacksubmision.di.Injection
import com.helsanf.jetpacksubmision.model.Movie
import com.helsanf.jetpacksubmision.model.TvShow
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private var viewModel : DetailViewModel? = null
    private var movieId: Int? = null
    private var tvShowId : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModel = obtainViewModel()
        val extras: Bundle? = intent.extras
        movieId = extras?.getInt("movie_id")
        tvShowId = extras?.getInt("tv_id")

        if(movieId != 0){
            progressDetail.visibility = View.VISIBLE
            viewModel?.setMovieId(movieId.toString())
            viewModel!!.getDetailMovie().observe(this, Observer {
                progressDetail.visibility = View.GONE
                getDetailMovie(it)
            })
        }else if(tvShowId != 0){
            progressDetail.visibility = View.VISIBLE
            viewModel?.setTvId(tvShowId!!)
            viewModel!!.getDetailTvShow().observe(this, Observer {
                progressDetail.visibility = View.GONE
                getDetailTvShow(it)
            })
        }

    }
    private fun obtainViewModel(): DetailViewModel {
        return ViewModelProviders.of(this, Injection().getMovieRepo(this)).get(DetailViewModel::class.java)
    }

    fun getDetailMovie(movie : ResultMovie){
        tvTitle.text = movie.title
        tv_release_date.text = movie.release_date
        tv_rating.text = movie.vote_average.toString()
        tv_overview.text = movie.overview
        movie.poster_path.let { Picasso.get().load("https://image.tmdb.org/t/p/w185/$it").into(imgDetail) }
    }

    fun getDetailTvShow(tvShow: ResultTvShow){
        tvTitle.text = tvShow.original_name
        tv_release_date.text = tvShow.first_air_date
        tv_rating.text = tvShow.vote_average.toString()
        tv_overview.text = tvShow.overview
        tvShow.poster_path.let { Picasso.get().load("https://image.tmdb.org/t/p/w185/$it").into(imgDetail) }
    }
}
