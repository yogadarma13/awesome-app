package com.yogadarma.core.data.source.remote.network

import com.yogadarma.core.data.source.remote.response.CuratedResponse
import com.yogadarma.core.data.source.remote.response.PhotoItem
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("curated")
    fun getCuratedPhoto(): Flowable<CuratedResponse>

    @GET("photos/{id}")
    fun getPhotoDetail(@Path("id") photoId: Int) : Flowable<PhotoItem>
}