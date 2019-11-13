package com.helsanf.jetpacksubmision.movie

import androidx.lifecycle.ViewModel
import com.helsanf.jetpacksubmision.model.Movie
import com.helsanf.jetpacksubmision.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovie(): List<Movie> {
        return DataDummy().generateMovie()
    }
}