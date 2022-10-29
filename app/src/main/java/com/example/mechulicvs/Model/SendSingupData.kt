package com.example.mechulicvs.Model

import org.json.JSONObject
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PUT

interface SendSingupData {

    @FormUrlEncoded
    @PUT("/users")
    suspend fun userSingup(
        @Field("user_id") id : String,
        @Field("user_pw") pw : String,
        @Field("user_nickname") nickname : String,
        @Field("signUpRatings") ratings : MutableMap<Int, Double>
    ) : SignupDataResult


}