package com.example.mechulicvs.viewmodel.recommend

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.repository.recommend.DetailAddRatingSetRepository

class DetailAddRatingSetViewModel(application: Application) : AndroidViewModel(application) {

    private var resultList = MutableLiveData<Boolean>()

    private val context = application.applicationContext

//    fun sendData(menuId : Int){
//        getmenuId.postValue(menuId)
//    }

    fun getResultRepository(): LiveData<Boolean> {
        return resultList
    }

    init {
        resultList = DetailAddRatingSetRepository.getResult()!!
    }

}