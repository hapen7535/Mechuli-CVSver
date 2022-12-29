package com.example.mechulicvs.model

import com.google.gson.annotations.SerializedName

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