package com.yogadarma.core.utils

import com.yogadarma.core.data.source.local.entity.PhotoEntity
import com.yogadarma.core.data.source.remote.response.PhotoItem
import com.yogadarma.core.domain.model.Photo

object DataMapper {
    fun mapListPhotoEntitiesToPhotoDomain(input: List<PhotoEntity>): List<Photo> {
        val list = input.map {
            mapPhotoEntityToDomain(it)
        }

        return list
    }

    fun mapListPhotoResponseToEntity(input: List<PhotoItem?>?): List<PhotoEntity> {
        val list = input?.map {
            mapPhotoResponseToEntity(it)
        }

        return list ?: listOf()
    }

    fun mapPhotoResponseToEntity(input: PhotoItem?): PhotoEntity =
        PhotoEntity(
            input?.id ?: 0,
            input?.src?.portrait ?: "",
            input?.src?.landscape ?: "",
            input?.photographer ?: ""
        )

    fun mapPhotoEntityToDomain(input: PhotoEntity): Photo =
        Photo(input.id, input.portraitPhotoUrl, input.landscapePhotoUrl, input.photographer)

}