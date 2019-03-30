package com.cdmp.rickmorty.di

import arrow.integrations.retrofit.adapter.CallKindAdapterFactory
import com.cdmp.rickmorty.data.RickMortyApi
import com.cdmp.rickmorty.data.service.RickMortyService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataModules {

    private const val BaseUrl = "https://rickandmortyapi.com/"

    val remoteServiceModule = module {
        single {
            OkHttpClient.Builder()
                .connectTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(10L, TimeUnit.SECONDS).build()
        }

        single {
            createWebService<RickMortyService>(
                get(),
                BaseUrl
            )
        }
    }

    val apiModule = module {
        single {
            RickMortyApi(get())
        }
    }
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    return retrofit.create(T::class.java)
}