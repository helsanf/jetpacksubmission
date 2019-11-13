package com.helsanf.jetpacksubmision.tvshow

import androidx.lifecycle.ViewModel
import com.helsanf.jetpacksubmision.model.TvShow
import com.helsanf.jetpacksubmision.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShow(): List<TvShow> {
        return DataDummy().generateDummyTvShow()
    }
}