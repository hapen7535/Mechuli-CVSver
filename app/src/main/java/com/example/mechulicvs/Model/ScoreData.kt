package com.example.mechulicvs.Model

import com.google.gson.annotations.SerializedName

data class ScoreData(

    val menuId : Int,
    val cvsName : String,
    val menuName : String,
    val menuImg : String,
    val score : Double,

    )
