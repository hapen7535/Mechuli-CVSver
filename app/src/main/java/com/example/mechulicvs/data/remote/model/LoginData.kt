package com.example.mechulicvs.data.remote.model

data class LoginDataResult (

    val isSuccess : Boolean,
    val result : getId

        )

data class getId (

    val user_id : String,
    val user_nickname : String,

        )