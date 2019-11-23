package com.helsanf.jetpacksubmision.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.helsanf.jetpacksubmision.data.datasource.movie.RepositoryMovie
import com.helsanf.jetpacksubmision.model.TvShow
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.TvShowResponses
import com.helsanf.jetpacksubmision.utils.DataDummy

class TvShowViewModel internal constructor(private val repository: RepositoryMovie): ViewModel() {
    fun getTvShow(): List<TvShow> {
        return DataDummy().generateDummyTvShow()
    }

    fun getAllTvShow() : LiveData<List<ResultTvShow>>{
        return  repository.getAllTvShow()
    }
}