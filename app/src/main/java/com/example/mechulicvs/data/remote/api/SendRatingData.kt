package com.example.mechulicvs.data.remote.api

import com.example.mechulicvs.data.remote.model.SetRatingData
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PUT

interface SendRatingData {

    @FormUrlEncoded
    @PUT("/recommends")
    suspend fun userSingup(
        @Field("user_id") id : String,
        @Field("menu_id") menuId : Int,
        @Field("score") ratings : Double,
    ) : SetRatingData

}