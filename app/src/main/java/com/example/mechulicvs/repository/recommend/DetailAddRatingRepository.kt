package com.example.mechulicvs.repository.recommend

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.di.MainApplication
import com.example.mechulicvs.data.remote.model.Result
import com.example.mechulicvs.data.remote.api.getRecomAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailAddRatingRepository {

    companion object {
        fun getResult(): MutableLiveData<Result> {

//            Log.d("keyword in repository", MainApplication.prefs.getString("keyword", ""))

            val UserRatingData: MutableLiveData<Result> = MutableLiveData<Result>()
            val userId = MainApplication.prefs.getString("userId", "")
            val menuId = MainApplication.prefs.getInt("menuId", 0)
//            Log.d("id in repository", MainApplication.prefs.getString("userId", ""))
//            Log.d("menuid in repository", MainApplication.prefs.getInt("menuId", 0).toString())

            CoroutineScope(Dispatchers.Default).launch {
                launch(Dispatchers.IO) {

                    var response =
                        getRecomAPI.modifyUserRatingService.getUserRatingData(userId, menuId)

                    withContext(Dispatchers.Default) {
                        response.let {
                            if (response.isSuccess) {
                                Log.d("list", response.userRatingData.toString())
                                UserRatingData.postValue(response.userRatingData)
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