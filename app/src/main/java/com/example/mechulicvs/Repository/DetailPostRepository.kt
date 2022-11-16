package com.example.mechulicvs.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.MainApplication
import com.example.mechulicvs.Model.PostDetail
import com.example.mechulicvs.Model.UserDataAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailPostRepository {


    companion object {

        fun getResult(): MutableLiveData<PostDetail> {

            val UserRatingData: MutableLiveData<PostDetail> = MutableLiveData<PostDetail>()
            val recipeId = MainApplication.prefs.getInt("recipeId", 0)
            Log.d("recipeId", recipeId.toString())

            CoroutineScope(Dispatchers.Default).launch {
                launch(Dispatchers.IO) {

                    var response =
                        UserDataAPI.getDetailPostService.getDetailPostData(recipeId)

                    withContext(Dispatchers.Default) {
                        response.let {
                            if (response.isSuccess) {
                                Log.d("상세 페이지 조회", response.message)
                                UserRatingData.postValue(response.result)
                            } else {
                                Log.d("error", response.message)
                            }
                        }
                    }
                }
            }
            return UserRatingData
        }
    }


}