package com.madmuv.marvelheroesclone.di

import com.madmuv.marvelheroesclone.model.Poster
import com.madmuv.marvelheroesclone.network.MarvelClient
import com.madmuv.marvelheroesclone.network.MarvelService
import com.madmuv.marvelheroesclone.network.RequestIntercepter
import com.skydoves.sandwich.ResponseDataSource
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BaseUrl = "https://gist.githubusercontent.com/skydoves/"
val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(RequestIntercepter())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(MarvelService::class.java) }

    single { MarvelClient(get()) }

    single { ResponseDataSource<List<Poster>>() }
}