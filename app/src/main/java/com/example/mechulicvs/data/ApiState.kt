package com.example.mechulicvs.data

sealed class ApiState<out T>{
    data class Success<out T>(val data: T? = null) : ApiState<T>()
    data class Loading(val nothing: Nothing?=null) : ApiState<Nothing>()
    data class Error(val msg: String?) : ApiState<Nothing>()
}
