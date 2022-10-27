package com.example.mechulicvs.Model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Menu::class], version = 1)
abstract class MenuDataBase : RoomDatabase() {
    abstract fun menuDao() : MenuDao
}