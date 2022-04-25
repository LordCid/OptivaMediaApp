package com.example.omapp.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "Favorites",
    primaryKeys = ["id"],
)
data class MovieFavorite(
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean
)
