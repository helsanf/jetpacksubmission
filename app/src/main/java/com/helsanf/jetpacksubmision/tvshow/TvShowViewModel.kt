package com.helsanf.jetpacksubmision.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.helsanf.jetpacksubmision.data.datasource.movie.RepositoryMovie
import com.helsanf.jetpacksubmision.model.TvShow
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.TvShowResponses
import com.helsanf.jetpacksubmision.utils.DataDummy

class TvShowViewModel internal constructor(private val repository: RepositoryMovie) : ViewModel() {

    val tvShow by lazy { repository.getAllTvShow() }

    private val tvShowFavorites = repository.getTvShowFromFavorites()
    private val liveDataTvShow = tvShowFavorites?.let { LivePagedListBuilder(it, 10).build() }

    val favoritesTvShow: LiveData<PagedList<ResultTvShow>>? get() = liveDataTvShow


}