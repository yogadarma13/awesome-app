package com.yogadarma.core.domain.repository

import com.yogadarma.core.data.Resource
import com.yogadarma.core.domain.model.Photo
import io.reactivex.rxjava3.core.Flowable

interface IRepository {
    fun getCuratedPhoto(): Flowable<Resource<List<Photo>>>
}