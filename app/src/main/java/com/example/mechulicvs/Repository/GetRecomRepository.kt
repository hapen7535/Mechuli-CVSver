package com.example.mechulicvs.Repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.Model.IdDataAPI
import com.example.mechulicvs.Model.MenuList
import dagger.hilt.android.internal.Contexts.getApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GetRecomRepository {

    companion object {
        fun getResult() : MutableLiveData<List<MenuList>>?{

            Log.d("viewmodel repository","viewmodel 진입getString(\"userId\", \"\")!!")

            val RecomListLiveData : MutableLiveData<List<MenuList>> = MutableLiveData<List<MenuList>>()

            CoroutineScope(Dispatchers.Default).launch {
                launch(Dispatchers.IO) {
                    var response = IdDataAPI.recomservice.getRecommend()
                    withContext(Dispatchers.Default){
                        response.let {
                            if(response.isSuccess){
                                RecomListLiveData.postValue(response.result)
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