package com.example.mechulicvs.Model

import com.google.gson.annotations.SerializedName

data class CommentData (
    val isSuccess : Boolean,
    val code : Int,
    val message : String,
    val result : Reply
        )