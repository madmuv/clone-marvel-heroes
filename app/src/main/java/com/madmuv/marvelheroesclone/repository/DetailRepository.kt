package com.madmuv.marvelheroesclone.repository

import com.madmuv.marvelheroesclone.persistence.PosterDao

class DetailRepository constructor(private val posterDao: PosterDao) : Repository {

    fun getPosterById(id: Long) = posterDao.getPoster(id)
}