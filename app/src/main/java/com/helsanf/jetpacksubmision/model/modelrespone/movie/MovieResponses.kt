package com.helsanf.jetpacksubmision.model.modelrespone.movie

data class MovieResponses(
    val page: Int,
    val results: List<ResultMovie>,
    val total_pages: Int,
    val total_results: Int
)