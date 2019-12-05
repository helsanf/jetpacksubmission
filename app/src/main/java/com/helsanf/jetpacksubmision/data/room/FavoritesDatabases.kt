package com.helsanf.jetpacksubmision.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.helsanf.jetpacksubmision.model.modelrespone.movie.ResultMovie
import com.helsanf.jetpacksubmision.model.modelrespone.movie.tvshow.ResultTvShow

@Database(entities = arrayOf(
        ResultMovie::class,
        ResultTvShow::class
),version = 1)
abstract class FavoritesDatabases : RoomDatabase() {
    abstract fun FavoritesDao() : FavoritesDao

    companion object {
        private var INSTANCE: FavoritesDatabases? = null
        fun getInstance(context: Context): FavoritesDatabases? {
            if (INSTANCE == null) {
                synchronized(FavoritesDatabases::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoritesDatabases::class.java,"favorites.db"
                    )
                        .build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }

    }
}