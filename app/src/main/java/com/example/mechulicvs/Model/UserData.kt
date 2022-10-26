package com.example.mechulicvs.Model

import java.io.Serializable

data class UserData(

    var userId : String,
    var userPw : String,
    var nickName : String,

) : Serializable
