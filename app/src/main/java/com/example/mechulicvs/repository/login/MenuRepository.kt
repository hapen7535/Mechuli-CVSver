package com.example.mechulicvs.repository.login

import com.example.mechulicvs.data.entity.MenuDataBase
import com.example.mechulicvs.data.remote.api.UserDataAPI
import com.example.mechulicvs.data.remote.api.UserDataAPI.signupMenuDataService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MenuRepository @Inject constructor(
    private val api : UserDataAPI,
    private val database : MenuDataBase
    ){

    private val menusDao = database.menuDao()

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