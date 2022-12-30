package com.example.mechulicvs.data.remote.model

import com.google.gson.annotations.SerializedName

data class SetRatingData(

    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("message") val message : String

)
