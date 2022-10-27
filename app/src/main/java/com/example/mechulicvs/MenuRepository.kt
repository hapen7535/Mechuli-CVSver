package com.example.mechulicvs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mechulicvs.Model.Menu
import com.example.mechulicvs.Model.MenuDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuRepository(private val database : MenuDataBase){
    fun getMenus() : LiveData<List<Menu>> {
        return database.menuDao().getAllMenu()
    }

    suspend fun refreshMenus(type : String?){
        withContext(Dispatchers.IO){

        }
    }
}