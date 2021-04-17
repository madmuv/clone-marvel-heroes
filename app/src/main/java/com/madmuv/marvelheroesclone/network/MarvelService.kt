package com.madmuv.marvelheroesclone.network

import com.madmuv.marvelheroesclone.model.Poster
import retrofit2.Call
import retrofit2.http.GET

const val service = "ae1f687d7e67865a3d217ff719e256f8/raw/592160d562604476acd2d4adfd9d383058c7c558/MarvelLists.json"
interface MarvelService {

    @GET(service)
    fun fetchMarvelPosterList(): Call<List<Poster>>
}