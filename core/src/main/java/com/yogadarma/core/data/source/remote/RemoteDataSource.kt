package com.yogadarma.core.data.source.remote

import com.yogadarma.core.data.source.remote.network.ApiResponse
import com.yogadarma.core.data.source.remote.network.ApiService
import com.yogadarma.core.data.source.remote.response.PhotoItem
import com.yogadarma.core.utils.EspressoIdlingResource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

/* Tolong comment kode Idling Resource jika ingin menjalankan unit test,
dan uncomment kode Idling Resoirce jika ingin menjalankan instrumentation test */
@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getCuratedPhoto(): Flowable<ApiResponse<List<PhotoItem?>?>> {
//        EspressoIdlingResource.increment()
        val resultData = PublishSubject.create<ApiResponse<List<PhotoItem?>?>>()

        apiService.getCuratedPhoto().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).take(1).subscribe({ response ->
                resultData.onNext(ApiResponse.Success(data = response.photos))
                resultData.onComplete()
//                EspressoIdlingResource.decrement()
            }, { error ->
                resultData.onNext(ApiResponse.Error(error))
                resultData.onComplete()
//                EspressoIdlingResource.decrement()
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getPhotoDetail(photoId: Int): Flowable<ApiResponse<PhotoItem?>> {
//        EspressoIdlingResource.increment()
        val resultData = PublishSubject.create<ApiResponse<PhotoItem?>>()

        apiService.getPhotoDetail(photoId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).take(1).subscribe({ response ->
                resultData.onNext(ApiResponse.Success(data = response))
                resultData.onComplete()
//                EspressoIdlingResource.decrement()
            }, { error ->
                resultData.onNext(ApiResponse.Error(error))
                resultData.onComplete()
//                EspressoIdlingResource.decrement()
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}