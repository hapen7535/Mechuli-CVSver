package com.example.mechulicvs

import androidx.lifecycle.LiveData
import com.example.mechulicvs.Model.Menu
import com.example.mechulicvs.Model.MenuDataBase
import com.example.mechulicvs.Model.UserDataAPI.signupMenuDataService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuRepository(
    private val database : MenuDataBase
    ){
    fun getMenus() : LiveData<List<Menu>>{
        return database.menuDao().getAllMenu()
    }
    suspend fun refreshMenus(type : String?){
        val result = withContext(Dispatchers.IO){
            signupMenuDataService.getSignupMenuData()
        }
        val ans = result
        if(ans.isSuccess){
            var menuListData = ans.result
            database.menuDao().insertAllMenu(menuListData)
        }
    }
}