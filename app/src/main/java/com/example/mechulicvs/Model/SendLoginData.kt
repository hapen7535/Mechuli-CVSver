package com.example.mechulicvs.Model

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SendLoginData {

    @FormUrlEncoded
    @POST("/users")
    suspend fun userLogin(
        @Field("user_id") id : String,
        @Field("user_pw") pw : String,
    ) : LoginDataResult

}