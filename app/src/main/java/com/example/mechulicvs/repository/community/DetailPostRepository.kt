package com.example.mechulicvs.repository.community

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.MainApplication
import com.example.mechulicvs.data.remote.api.GetPostDetailData
import com.example.mechulicvs.data.remote.model.PostDetail
import com.example.mechulicvs.data.remote.api.UserDataAPI
import com.example.mechulicvs.data.remote.model.PostDetailData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

//class DetailPostRepository {
//
//
//        fun getResult(): MutableLiveData<PostDetail> {
//
//            val UserRatingData: MutableLiveData<PostDetail> = MutableLiveData<PostDetail>()
//            val recipeId = MainApplication.prefs.getInt("recipeId", 0)
//            Log.d("recipeId", recipeId.toString())
//
//            CoroutineScope(Dispatchers.Default).launch {
//                launch(Dispatchers.IO) {
//
//                    var response =
//                        UserDataAPI.getDetailPostService.getDetailPostData(recipeId)
//
//                    withContext(Dispatchers.Default) {
//                        response.let {
//                            if (response.isSuccess) {
//                                Log.d("상세 페이지 조회", response.message)
//                                UserRatingData.postValue(response.result)
//                            } else {
//                                Log.d("error", response.message)
//                            }
//                        }
//                    }
//                }
//            }
//            return UserRatingData
//        }
//
//
//}

class DetailPostRepository @Inject constructor(
    private val detailPostService : GetPostDetailData
){

    suspend fun getDetailPostInfo(recipeId : Int): PostDetailData {
        return detailPostService.getDetailPostData(recipeId)
    }
}