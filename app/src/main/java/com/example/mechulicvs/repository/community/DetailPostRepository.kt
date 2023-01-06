package com.example.mechulicvs.repository.community

import com.example.mechulicvs.DetailPostApiHelper
import com.example.mechulicvs.data.DetailPostDataSource
import com.example.mechulicvs.data.remote.api.community.GetPostDetailData
import com.example.mechulicvs.data.remote.model.PostDetailData
import retrofit2.Response
import javax.inject.Inject

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
    private val detailPostApiHelper: DetailPostApiHelper
) {
    suspend fun getDetailPost() = detailPostApiHelper.getDetailPost()
}