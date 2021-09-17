package com.yogadarma.core.data.source.remote.network

import com.yogadarma.core.data.source.remote.response.CuratedResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("curated")
    fun getCuratedPhoto(): Flowable<CuratedResponse>
}