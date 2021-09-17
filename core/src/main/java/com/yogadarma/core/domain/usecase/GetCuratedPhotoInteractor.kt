package com.yogadarma.core.domain.usecase

import com.yogadarma.core.data.Resource
import com.yogadarma.core.domain.model.Photo
import com.yogadarma.core.domain.repository.IRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetCuratedPhotoInteractor @Inject constructor(private val repository: IRepository) :
    GetCuratedPhotoUseCase {

    override fun getCuratedPhoto(): Flowable<Resource<List<Photo>>> = repository.getCuratedPhoto()
}