package com.example.mechulicvs.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.MainApplication
import com.example.mechulicvs.Model.IdDataAPI
import com.example.mechulicvs.Model.KeywordDataAPI
import com.example.mechulicvs.Model.MenuList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetRatingListRepository {

    companion object {
        fun getResult() : MutableLiveData<List<MenuList>>?{

            Log.d("keyword in repository", MainApplication.prefs.getString("keyword", ""))

            val RatingListLiveData : MutableLiveData<List<MenuList>> = MutableLiveData<List<MenuList>>()

            CoroutineScope(Dispatchers.Default).launch {
                launch(Dispatchers.IO) {
                    Log.d("keyword in repository", MainApplication.prefs.getString("keyword", ""))
                    KeywordDataAPI.keyword = MainApplication.prefs.getString("keyword", "")
                    var response = KeywordDataAPI.getRatingDataService.getRatingList()
                    withContext(Dispatchers.Default){
                        response.let {
                            if(response.isSuccess){
                                Log.d("list", response.result.toString())
                                RatingListLiveData.postValue(response.result)
                            } else{
                                Log.d("error", response.message)
                            }
                        }
                    }
                }
            }
            return RatingListLiveData
        }
    }

}