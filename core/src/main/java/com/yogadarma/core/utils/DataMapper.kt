package com.yogadarma.core.utils

import com.yogadarma.core.data.source.local.entity.PhotoEntity
import com.yogadarma.core.data.source.remote.response.PhotoItem
import com.yogadarma.core.domain.model.Photo

object DataMapper {
    fun mapListPhotoEntitiesToPhotoDomain(input: List<PhotoEntity>): List<Photo> {
        val list = input.map {
            Photo(it.id, it.portraitPhotoUrl, it.landscapePhotoUrl, it.photographer)
        }

        return list
    }

    fun mapListPhotoResponseToEntity(input: List<PhotoItem?>?): List<PhotoEntity> {
        val list = input?.map {
            PhotoEntity(
                it?.id ?: 0,
                it?.src?.portrait ?: "",
                it?.src?.landscape ?: "",
                it?.photographer ?: ""
            )
        }

        return list ?: listOf()
    }
}