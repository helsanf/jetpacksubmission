package com.helsanf.jetpacksubmision.model.modelrespone.movie

import androidx.room.Entity
import androidx.room.PrimaryKey


data class MovieResponses(

   val page: Int,
    val results: List<ResultMovie>,
    val total_pages: Int,
    val total_results: Int
)