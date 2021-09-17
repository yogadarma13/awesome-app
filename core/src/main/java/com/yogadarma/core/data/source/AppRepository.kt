package com.yogadarma.core.data.source

import com.yogadarma.core.data.Resource
import com.yogadarma.core.data.source.remote.RemoteDataSource
import com.yogadarma.core.data.source.remote.network.ApiResponse
import com.yogadarma.core.data.source.remote.response.PhotoItem
import com.yogadarma.core.domain.model.Photo
import com.yogadarma.core.domain.repository.IRepository
import com.yogadarma.core.utils.DataMapper
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    IRepository {

    override fun getCuratedPhoto(): Flowable<Resource<List<Photo>>> =
        object : NetworkBoundResource<List<Photo>, List<PhotoItem?>?>() {
            override fun createCall(): Flowable<ApiResponse<List<PhotoItem?>?>> =
                remoteDataSource.getCuratedPhoto()

            override fun loadData(data: List<PhotoItem?>?): List<Photo> =
                DataMapper.mapListPhotoItemToListPhoto(data)

        }.asFlowable()
}