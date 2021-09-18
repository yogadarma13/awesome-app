package com.yogadarma.awesomeapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.yogadarma.core.data.Resource
import com.yogadarma.core.domain.model.Photo
import com.yogadarma.core.domain.usecase.GetDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getDetailUseCase: GetDetailUseCase) :
    ViewModel() {

    fun getPhotoDetail(photoId: Int): LiveData<Resource<Photo>> =
        LiveDataReactiveStreams.fromPublisher(getDetailUseCase.getPhotoDetail(photoId))
}