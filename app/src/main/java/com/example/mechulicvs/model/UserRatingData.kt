package com.example.mechulicvs.model

import com.google.gson.annotations.SerializedName

data class UserRatingData(

    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("result") val userRatingData : Result,

)

data class Result(

    @SerializedName("menu_id") val menuId : Int,
    @SerializedName("cvs_name") val cvsName : String,
    @SerializedName("menu_name") val menuName : String,
    @SerializedName("menu_image") val menuImg : String,
    val score : Double,

)
