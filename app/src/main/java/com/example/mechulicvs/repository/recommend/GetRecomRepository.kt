package com.example.mechulicvs.repository.recommend

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.di.MainApplication
import com.example.mechulicvs.data.remote.model.MenuList
import com.example.mechulicvs.data.remote.api.getRecomAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GetRecomRepository {

   companion object {
        fun getResult() : MutableLiveData<List<MenuList>>{

            val userid = MainApplication.prefs.getString("userId", "")

            val RecomListLiveData : MutableLiveData<List<MenuList>> = MutableLiveData<List<MenuList>>()

            CoroutineScope(Dispatchers.Default).launch {
                launch(Dispatchers.IO) {
                    var response = getRecomAPI.recomservice.getRecommend(userid)
                    withContext(Dispatchers.Default){
                        response.let {
                            if(response.isSuccess){
                                RecomListLiveData.postValue(response.result)
                                Log.d("result", response.result.toString())
                            } else{
                                Log.d("error", response.message)
                            }
                        }
                    }
                }
            }
            return RecomListLiveData
        }


    }

}