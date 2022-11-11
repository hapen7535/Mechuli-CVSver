package com.example.mechulicvs.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.MainApplication
import com.example.mechulicvs.Model.Result
import com.example.mechulicvs.Model.UserDataAPI
import com.example.mechulicvs.Model.getRecomAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailAddRatingSetRepository {

    companion object {

//        var sendMenuId = MutableLiveData<Int>()

        fun getResult(): MutableLiveData<Boolean> {

            val UserRatingData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
            val userId = MainApplication.prefs.getString("userId", "")
            val menuId = MainApplication.prefs.getInt("menuId", 0)
            val userRating = MainApplication.prefs.getFloat("rating", 0.0.toFloat())

            CoroutineScope(Dispatchers.Default).launch {
                launch(Dispatchers.IO) {

                    var response =
                        UserDataAPI.setRatingService.userSingup(userId, menuId, userRating.toDouble())

                    withContext(Dispatchers.Default) {
                        response.let {
                            if (response.isSuccess) {
                                Log.d("수정 성공", menuId.toString() + response.message)
                                UserRatingData.postValue(response.isSuccess)
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