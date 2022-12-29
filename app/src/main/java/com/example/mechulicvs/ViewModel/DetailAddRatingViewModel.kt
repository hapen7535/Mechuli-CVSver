package com.example.mechulicvs.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.model.Result
import com.example.mechulicvs.Repository.DetailAddRatingRepository

class DetailAddRatingViewModel(application: Application) : AndroidViewModel(application) {

    private var resultList = MutableLiveData<Result>()
    private var getmenuId = MutableLiveData<Int>()

    private val context = application.applicationContext

//    fun sendData(menuId : Int){
//        getmenuId.postValue(menuId)
//    }

    fun getResultRepository(): LiveData<Result> {
        return resultList
    }

    init {
        resultList = DetailAddRatingRepository.getResult()!!
    }
}