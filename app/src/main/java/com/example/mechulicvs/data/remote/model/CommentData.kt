package com.example.mechulicvs.data.remote.model

import com.example.mechulicvs.data.remote.model.Reply

data class CommentData (
    val isSuccess : Boolean,
    val code : Int,
    val message : String,
    val result : Reply
        )