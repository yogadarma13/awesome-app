package com.yogadarma.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "photo")
data class PhotoEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "portraitPhotoUrl")
    val portraitPhotoUrl: String,

    @ColumnInfo(name = "landscapePhotoUrl")
    val landscapePhotoUrl: String,

    @ColumnInfo(name = "photographer")
    val photographer: String
)