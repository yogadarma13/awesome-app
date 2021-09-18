package com.yogadarma.core.domain.usecase

import com.yogadarma.core.data.Resource
import com.yogadarma.core.domain.model.Photo
import com.yogadarma.core.domain.repository.IRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetDetailInteractor @Inject constructor(private val repository: IRepository) :
    GetDetailUseCase {

    override fun getPhotoDetail(photoId: Int): Flowable<Resource<Photo>> =
        repository.getPhotoDetail(photoId)
}