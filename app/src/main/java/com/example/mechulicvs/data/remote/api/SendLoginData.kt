package com.example.mechulicvs.data.remote.api

import com.example.mechulicvs.data.remote.model.LoginDataResult
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