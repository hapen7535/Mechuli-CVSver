package com.example.mechulicvs.Model

import java.lang.IllegalStateException

data class MenuDataResult (
    val isSuccess : Boolean,
    val result : MenuList
        )

data class MenuList (
    val menu_id : Int,
    val store_name : String,
    val menu_name : String,
    val menu_image : String,
        )