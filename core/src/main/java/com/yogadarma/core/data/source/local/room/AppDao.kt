package com.yogadarma.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yogadarma.core.data.source.local.entity.PhotoEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPhoto(photos: List<PhotoEntity>) : Completable

    @Query("SELECT * FROM photo")
    fun getAllPhoto(): Flowable<List<PhotoEntity>>
}