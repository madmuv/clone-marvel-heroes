package com.madmuv.marvelheroesclone.network

import com.madmuv.marvelheroesclone.model.Poster
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.DataRetainPolicy
import com.skydoves.sandwich.ResponseDataSource
import com.skydoves.sandwich.disposables.CompositeDisposable

class MarvelClient(private val marvelService: MarvelService) {

    fun fetchMarvelPosters(
        dataSource: ResponseDataSource<List<Poster>>,
        disposable: CompositeDisposable,
        onResult: (response: ApiResponse<List<Poster>>) -> Unit
    ) {
        dataSource
            .dataRetainPolicy(DataRetainPolicy.RETAIN)
            .retry(3, 5000L)
            .joinDisposable(disposable)
            .combine(marvelService.fetchMarvelPosterList(), onResult)
            .request()
    }
}