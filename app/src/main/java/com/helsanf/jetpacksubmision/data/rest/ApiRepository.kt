package com.helsanf.jetpacksubmision.data.source.remote.rest

import android.os.NetworkOnMainThreadException
import com.google.gson.GsonBuilder
import com.helsanf.jetpacksubmision.utils.NetworkConnectionInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository {
    fun getUrl(): Retrofit {
        var retrofit: Retrofit? = null
        var networkConnection : NetworkConnectionInterceptor
        val gson = GsonBuilder()
            .setLenient()
            .create()
//        val noInternet = OkHttpClient.Builder()
//        noInternet.addInterceptor(NetworkOnMainThreadException())


        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
        return retrofit
    }
}