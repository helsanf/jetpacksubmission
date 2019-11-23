package com.helsanf.jetpacksubmision.model.modelrespone.movie

data class ResultMovie(
    val id: Int,
    val title : String,
    val vote_average : String,
    val overview : String,
    val release_date : String,
    val poster_path : String
)