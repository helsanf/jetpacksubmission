package com.helsanf.jetpacksubmision.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private var viewModel : DetailViewModel? = null
    private var movieId: Int? = null
    private var tvShowId : Int? = null
    private var fromFavorites : Boolean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModel = obtainViewModel()
        val extras: Bundle? = intent.extras
        movieId = extras?.getInt("movie_id")
        tvShowId = extras?.getInt("tv_id")
        fromFavorites = extras?.getBoolean("fromfavorites",false)
        when(fromFavorites){
            true ->{
                btnFavorites.text = "Hapus Dari Daftar Favorite Anda"

            }
            false ->{
                btnFavorites.text = "Tambahkan Ke Daftar Favorite Anda"

            }
        }
//

        if(movieId != 0 && fromFavorites==false){
            progressDetail.visibility = View.VISIBLE
            viewModel?.setMovieId(movieId.toString())
            initDetailMovie()
        }else if(tvShowId != 0 && fromFavorites==false){
            progressDetail.visibility = View.VISIBLE
            viewModel?.setTvId(tvShowId!!)
            initDetailTvShow()
        } else if(movieId != 0 && fromFavorites==true){
            progressDetail.visibility = View.VISIBLE
            viewModel?.setMovieId(movieId.toString())
            viewModel?.getDetailMovieFromFavorites()?.observe(this, Observer {
                progressDetail.visibility = View.GONE
                if(it != null){
                    getDetailMovie(it)

                }
            })
        } else if(tvShowId != 0 && fromFavorites==true){
            progressDetail.visibility = View.VISIBLE
            viewModel?.setTvId(tvShowId!!)
            viewModel?.getDetailTvShowFromFavorites()?.observe(this, Observer {
                progressDetail.visibility = View.GONE
                if(it !=null){
                    getDetailTvShow(it)

                }
//                finish()
            })
        }


    }
    private fun obtainViewModel(): DetailViewModel {
        return ViewModelProviders.of(this, Injection().getMovieRepo(this)).get(DetailViewModel::class.java)
    }
    private fun initDetailMovie() = GlobalScope.launch(Dispatchers.Main){
        val detailMovieGet = viewModel!!.getDetailMovie.await()
        detailMovieGet.observe(this@DetailActivity, Observer {
            progressDetail.visibility = View.GONE
            getDetailMovie(it)
        })
    }
    private fun initDetailTvShow() = GlobalScope.launch(Dispatchers.Main){
        val detailTvShowGet = viewModel!!.getDetailTvShow.await()
        detailTvShowGet.observe(this@DetailActivity, Observer {
            progressDetail.visibility = View.GONE
            getDetailTvShow(it)
        })
    }

    fun getDetailMovie(movie : ResultMovie){
        tvTitle.text = movie.title
        tv_release_date.text = movie.release_date
        tv_rating.text = movie.vote_average.toString()
        tv_overview.text = movie.overview
        movie.poster_path.let { Picasso.get().load("https://image.tmdb.org/t/p/w185/$it").into(imgDetail) }
        btnFavorites.setOnClickListener {
            when(fromFavorites){
                true ->{
                    viewModel?.unFavoritesMovie(movie)
                    Toast.makeText(this,"Berhasil Menghapus dari List Favorite", Toast.LENGTH_SHORT).show()
                    finish()
                }
                false ->{

                    viewModel?.addToFavoritesMovie(movie)
                    Toast.makeText(this,"Berhasil Menambahkan ke List Favorite", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getDetailTvShow(tvShow: ResultTvShow){
        tvTitle.text = tvShow.original_name
        tv_release_date.text = tvShow.first_air_date
        tv_rating.text = tvShow.vote_average.toString()
        tv_overview.text = tvShow.overview
        tvShow.poster_path.let { Picasso.get().load("https://image.tmdb.org/t/p/w185/$it").into(imgDetail) }
        btnFavorites.setOnClickListener {
            when(fromFavorites){
                true ->{
                    viewModel?.unFavoritesTvShow(tvShow)
                    Toast.makeText(this,"Berhasil Menghapus dari List Favorite", Toast.LENGTH_SHORT).show()
                    finish()
                }
                false ->{
                    viewModel?.addtoFavoritesTvShow(tvShow)
                    Toast.makeText(this,"Berhasil Menambahkan ke List Favorite", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

}
