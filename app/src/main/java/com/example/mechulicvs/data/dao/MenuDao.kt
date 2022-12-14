package com.example.mechulicvs.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mechulicvs.data.remote.model.Menu
import com.example.mechulicvs.data.remote.model.MenuList

@Dao
interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //Primary Key가 겹칠 때, 덮어쓴다.
    fun insertAllMenu(menus: List<MenuList>)

    @Delete
    fun delete(menu : Menu)

    @Query("SELECT * FROM menu")
    fun getAllMenu() : LiveData<List<Menu>>

    @Query("SELECT store_name FROM menu")
    fun getStoreName() : List<String>

    @Query("SELECT menu_name FROM menu")
    fun getMenuName() : List<String>

    @Query("SELECT menu_image FROM menu")
    fun getMenuImg() : List<String>

}