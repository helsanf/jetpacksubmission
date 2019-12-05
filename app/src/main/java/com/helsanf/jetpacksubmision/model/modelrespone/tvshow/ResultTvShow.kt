package com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_tvshow")
data class ResultTvShow(
    @PrimaryKey val id : Int,
    val original_name : String,
    val vote_average : String,
    val overview : String,
    val first_air_date : String,
    val poster_path : String
)