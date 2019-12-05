package com.helsanf.jetpacksubmision.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.helsanf.jetpacksubmision.ViewModelFactory
import com.helsanf.jetpacksubmision.data.datasource.movie.RepositoryMovie
import com.helsanf.jetpacksubmision.data.local.LocalRepository
import com.helsanf.jetpacksubmision.data.rest.remote.RemoteRepository
import com.helsanf.jetpacksubmision.data.room.FavoritesDatabases
import com.helsanf.jetpacksubmision.data.source.remote.rest.ApiInterface
import com.helsanf.jetpacksubmision.data.source.remote.rest.ApiRepository
import java.util.concurrent.Executors

class Injection {
    fun repository(context: Context): RepositoryMovie {
        val remoteRepository =
            RemoteRepository(ApiRepository().getUrl().create(ApiInterface::class.java))
        return RepositoryMovie(remoteRepository,database(context)).getInstance(remoteRepository,database(context))
    }

    fun getMovieRepo(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(repository(context))
    }

    fun database(context: Context): LocalRepository {
        val database: FavoritesDatabases? = FavoritesDatabases.getInstance(context)
        return LocalRepository(database?.FavoritesDao(), Executors.newSingleThreadExecutor())
    }


}