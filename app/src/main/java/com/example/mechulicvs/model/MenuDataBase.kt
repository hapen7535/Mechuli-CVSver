package com.example.mechulicvs.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//싱글톤 패턴으로 작성
@Database(entities = [Menu::class], version = 1)
abstract class MenuDataBase : RoomDatabase() {
    abstract fun menuDao() : MenuDao

    companion object{
        private var instance : MenuDataBase? = null

        @Synchronized
        fun getInstance(context : Context) : MenuDataBase? {
            if(instance == null){
                synchronized(MenuDataBase::class){ //synchronized : ThreadSafe하게 인스턴스를 사용하기 위함.
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