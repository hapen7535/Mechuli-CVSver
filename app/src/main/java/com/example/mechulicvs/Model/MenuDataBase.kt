package com.example.mechulicvs.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Menu::class], version = 1)
abstract class MenuDataBase : RoomDatabase() {
    abstract fun menuDao() : MenuDao

    companion object{
        private var instance : MenuDataBase? = null

        @Synchronized
        fun getInstance(context : Context) : MenuDataBase? {
            if(instance == null){
                synchronized(MenuDataBase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MenuDataBase::class.java,
                        "menu database"
                    ).build()
                }
            }
            return instance
        }
    }
}