package com.example.mechulicvs.data.remote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu")
data class Menu(
    @PrimaryKey val menu_id : Int,
    @ColumnInfo(name = "store_name") val storeName : String?,
    @ColumnInfo(name = "menu_name") val menuName : String?,
    @ColumnInfo(name = "menu_image") val menuImage : String?,
)
