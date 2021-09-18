package com.yogadarma.core.data.source

import com.yogadarma.core.data.Resource
import com.yogadarma.core.data.source.local.LocalDataSource
import com.yogadarma.core.data.source.remote.RemoteDataSource
import com.yogadarma.core.data.source.remote.network.ApiResponse
import com.yogadarma.core.data.source.remote.response.PhotoItem
import com.yogadarma.core.domain.model.Photo
import com.yogadarma.core.domain.repository.IRepository
import com.yogadarma.core.utils.DataMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IRepository {

    override fun getCuratedPhoto(): Flowable<Resource<List<Photo>>> =
        object : NetworkBoundResource<List<Photo>, List<PhotoItem?>?>() {
            override fun loadFromDB(): Flowable<List<Photo>> = localDataSource.getAllPhoto()
                .map { DataMapper.mapListPhotoEntitiesToPhotoDomain(it) }

            override fun shouldFetch(data: List<Photo>?): Boolean = true

            override fun createCall(): Flowable<ApiResponse<List<PhotoItem?>?>> =
                remoteDataSource.getCuratedPhoto()

            override fun saveCallResult(data: List<PhotoItem?>?) {
                val photoList = DataMapper.mapListPhotoResponseToEntity(data)
                localDataSource.insertAllPhoto(photoList).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

        }.asFlowable()

    override fun getPhotoDetail(photoId: Int): Flowable<Resource<Photo>> =
        object : NetworkBoundResource<Photo, PhotoItem?>() {
            override fun loadFromDB(): Flowable<Photo> = localDataSource.getPhotoDetail(photoId)
                .map { DataMapper.mapPhotoEntityToDomain(it) }

            override fun shouldFetch(data: Photo?): Boolean = true

            override fun createCall(): Flowable<ApiResponse<PhotoItem?>> =
                remoteDataSource.getPhotoDetail(photoId)

            override fun saveCallResult(data: PhotoItem?) {
                val photoEntity = DataMapper.mapPhotoResponseToEntity(data)
                localDataSource.updatePhoto(photoEntity).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

        }.asFlowable()
}