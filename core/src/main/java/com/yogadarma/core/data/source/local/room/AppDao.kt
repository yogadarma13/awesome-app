package com.yogadarma.core.data.source.local.room

import androidx.room.*
import com.yogadarma.core.data.source.local.entity.PhotoEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPhoto(photos: List<PhotoEntity>): Completable

    @Query("SELECT * FROM photo")
    fun getAllPhoto(): Flowable<List<PhotoEntity>>

    @Update
    fun updatePhoto(photo: PhotoEntity): Completable

    @Query("SELECT * FROM photo WHERE id = :photoId")
    fun getPhotoDetail(photoId: Int): Flowable<PhotoEntity>
}