package com.example.mechulicvs.data.remote.model

import java.io.Serializable

data class UserData(

    var userId : String,
    var userPw : String,
    var nickName : String,

) : Serializable
