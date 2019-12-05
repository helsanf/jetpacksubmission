package com.helsanf.jetpacksubmision.model.modelrespone.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_movie")
data class ResultMovie(
    @PrimaryKey val id: Int,
    val title : String,
    val vote_average : String,
    val overview : String,
    val release_date : String,
    val poster_path : String
)