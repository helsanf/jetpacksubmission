package com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow

data class TvShowResponses(
    val page: Int,
    val results: List<ResultTvShow>,
    val total_pages: Int,
    val total_results: Int
)