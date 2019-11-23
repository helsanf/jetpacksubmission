package com.helsanf.jetpacksubmision.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.helsanf.jetpacksubmision.ViewModelFactory
import com.helsanf.jetpacksubmision.data.datasource.movie.RepositoryMovie
import com.helsanf.jetpacksubmision.data.rest.remote.RemoteRepository
import com.helsanf.jetpacksubmision.data.source.remote.rest.ApiInterface
import com.helsanf.jetpacksubmision.data.source.remote.rest.ApiRepository

class Injection {
    fun repository(): RepositoryMovie {
        val remoteRepository =
            RemoteRepository(ApiRepository().getUrl().create(ApiInterface::class.java))
        return RepositoryMovie(remoteRepository).getInstance(remoteRepository)
    }
    fun getMovieRepo(context: Context) : ViewModelProvider.Factory{
        return ViewModelFactory(repository())
    }




}