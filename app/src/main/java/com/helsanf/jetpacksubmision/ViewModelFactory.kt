package com.helsanf.jetpacksubmision

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.helsanf.jetpacksubmision.data.datasource.movie.RepositoryMovie
import com.helsanf.jetpacksubmision.detail.DetailViewModel
import com.helsanf.jetpacksubmision.di.Injection
import com.helsanf.jetpacksubmision.movie.MovieViewModel
import com.helsanf.jetpacksubmision.tvshow.TvShowViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private var movieRepositoryMovie: RepositoryMovie) : ViewModelProvider.Factory{


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(movieRepositoryMovie) as T
        } else if (modelClass.isAssignableFrom(TvShowViewModel::class.java)) {
            return TvShowViewModel(movieRepositoryMovie) as T
        }else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movieRepositoryMovie) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name) as Throwable
    }


}