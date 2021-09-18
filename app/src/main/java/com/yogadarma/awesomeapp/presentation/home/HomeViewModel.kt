package com.yogadarma.awesomeapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yogadarma.core.data.Resource
import com.yogadarma.core.domain.model.Photo
import com.yogadarma.core.domain.usecase.GetCuratedPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getCuratedPhotoUseCase: GetCuratedPhotoUseCase) :
    ViewModel() {

    private val isListLiveData = MutableLiveData(true)

    fun setListView(isList: Boolean) {
        isListLiveData.postValue(isList)
    }

    fun isList(): LiveData<Boolean> = isListLiveData

    fun getCuratedPhoto(): LiveData<Resource<List<Photo>>> =
        LiveDataReactiveStreams.fromPublisher(getCuratedPhotoUseCase.getCuratedPhoto())
}