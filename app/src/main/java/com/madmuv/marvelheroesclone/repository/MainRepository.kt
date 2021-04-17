package com.madmuv.marvelheroesclone.repository

import androidx.lifecycle.MutableLiveData
import com.madmuv.marvelheroesclone.mapper.ErrorResponseMapper
import com.madmuv.marvelheroesclone.model.Poster
import com.madmuv.marvelheroesclone.network.MarvelClient
import com.madmuv.marvelheroesclone.persistence.PosterDao
import com.skydoves.sandwich.*
import com.skydoves.sandwich.disposables.CompositeDisposable
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainRepository constructor(
    private val marvelClient: MarvelClient,
    private val marvelDataSource: ResponseDataSource<List<Poster>>,
    private val posterDao: PosterDao
) : Repository {

    init {
        Timber.d("Injection MainRepository")
    }

    suspend fun loadMarvelPosters(
        disposable: CompositeDisposable,
        error: (String?) -> Unit
    ) = withContext(Dispatchers.IO) {
        val liveData = MutableLiveData<List<Poster>>()
        val posters = posterDao.getPosterList()
        if (posters.isEmpty()) {
            marvelClient.fetchMarvelPosters(marvelDataSource, disposable) { apiResponse ->
                apiResponse
                    .onSuccess {
                        data.whatIfNotNull {
                            liveData.postValue(it)
                            posterDao.insertPosterList(it)
                        }
                    }
                    .onError {
                        map(ErrorResponseMapper) {
                            error("[Code: $code]: $message")
                        }
                    }
                    .onException { error(message) }
            }
        }
        liveData.apply { postValue(posters) }
    }
}