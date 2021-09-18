package com.yogadarma.core.domain.usecase

import com.yogadarma.core.data.Resource
import com.yogadarma.core.domain.model.Photo
import io.reactivex.rxjava3.core.Flowable

interface GetDetailUseCase {
    fun getPhotoDetail(photoId: Int): Flowable<Resource<Photo>>
}