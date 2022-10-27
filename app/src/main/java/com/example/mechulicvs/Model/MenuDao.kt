package com.example.mechulicvs.Model

import androidx.room.*

@Dao
interface MenuDao {

    @Insert
    fun insertAllMenu(vararg menus : Menu)

    @Delete
    fun delete(menu : Menu)

    @Query("SELECT * FROM menu")
    fun getAllMenu() : List<Menu>

    @Query("SELECT store_name FROM menu")
    fun getStoreName() : List<String>

    @Query("SELECT menu_name FROM menu")
    fun getMenuName() : List<String>

    @Query("SELECT menu_image FROM menu")
    fun getMenuImg() : List<String>

}