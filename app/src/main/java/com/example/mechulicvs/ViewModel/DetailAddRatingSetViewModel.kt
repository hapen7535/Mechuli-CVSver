package com.example.mechulicvs.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.Model.Result
import com.example.mechulicvs.Repository.DetailAddRatingRepository
import com.example.mechulicvs.Repository.DetailAddRatingSetRepository

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