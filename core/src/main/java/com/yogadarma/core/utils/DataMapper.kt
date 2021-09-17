package com.yogadarma.core.utils

import com.yogadarma.core.data.source.remote.response.PhotoItem
import com.yogadarma.core.domain.model.Photo

object DataMapper {
    fun mapListPhotoItemToListPhoto(input: List<PhotoItem?>?): List<Photo> {
        val list = input?.map {
            Photo(it?.id ?: 0, it?.src?.medium ?: "", it?.photographer ?: "")
        }

        return list ?: listOf()
    }
}