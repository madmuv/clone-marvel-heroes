package com.madmuv.marvelheroesclone.di

import okhttp3.OkHttpClient
import org.koin.dsl.module

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(RequestIn)
    }
}