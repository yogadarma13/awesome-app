package com.yogadarma.core.data.source.local

import com.yogadarma.core.data.source.local.entity.PhotoEntity
import com.yogadarma.core.data.source.local.room.AppDao
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val appDao: AppDao) {
    fun insertAllPhoto(photos: List<PhotoEntity>) = appDao.insertAllPhoto(photos)

    fun getAllPhoto(): Flowable<List<PhotoEntity>> = appDao.getAllPhoto()
}