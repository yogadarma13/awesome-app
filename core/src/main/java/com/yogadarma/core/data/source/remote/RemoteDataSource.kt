package com.yogadarma.core.data.source.remote

import com.yogadarma.core.data.source.remote.network.ApiResponse
import com.yogadarma.core.data.source.remote.network.ApiService
import com.yogadarma.core.data.source.remote.response.PhotoItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getCuratedPhoto(): Flowable<ApiResponse<List<PhotoItem?>?>> {
        val resultData = PublishSubject.create<ApiResponse<List<PhotoItem?>?>>()

        apiService.getCuratedPhoto().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).take(1).subscribe({ response ->
                resultData.onNext(ApiResponse.Success(data = response.photos))
            }, { error ->
                resultData.onNext(ApiResponse.Error(error))
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}