package com.helsanf.jetpacksubmision.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.helsanf.jetpacksubmision.data.datasource.movie.RepositoryMovie
import com.helsanf.jetpacksubmision.model.Movie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.utils.DataDummy

class MovieViewModel internal constructor(private val repository: RepositoryMovie) : ViewModel() {

    val movieList by lazy { repository.getMovieList() }

    private val movieFavoriteList = repository.getMovieFromFavorites()
    private val liveDataMovie = movieFavoriteList?.let { LivePagedListBuilder(it, 10).build() }

    val favoritesMovie: LiveData<PagedList<ResultMovie>>? get() = liveDataMovie
}