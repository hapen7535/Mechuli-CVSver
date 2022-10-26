package com.example.mechulicvs.Controller

import com.example.mechulicvs.ApiState
import com.example.mechulicvs.Model.IdDataAPI
import com.example.mechulicvs.Model.LoginDataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

//class IdDuplicateData (
//    private val IdResultClient = IdDataAPI.retrofit
//) {
//    suspend fun getIdResult(id : String) : Flow<ApiState<LoginDataResult>> = flow {
//        emit(flowCall { IdResultClient. })
//    }.flowOn(Dispatchers.IO)
//}