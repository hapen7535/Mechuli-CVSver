package com.example.mechulicvs

import com.example.mechulicvs.data.remote.model.PostDetailData
import dagger.Provides
import retrofit2.Response


interface DetailPostApiHelper {
    suspend fun getDetailPost() : Response<PostDetailData>
}