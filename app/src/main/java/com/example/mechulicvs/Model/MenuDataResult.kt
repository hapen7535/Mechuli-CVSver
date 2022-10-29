package com.example.mechulicvs.Model

import com.google.gson.annotations.SerializedName
import java.lang.IllegalStateException

data class MenuDataResult (
    val isSuccess : Boolean,
    val result : List<MenuList>,
    val message : String
        )

data class MenuList (
    val menu_id : Int,
    @SerializedName("cvs_name")val store_name : String,
    val menu_name : String,
    val menu_image : String,
        ) {
            constructor() : this(0, "", "", "")
}