package com.example.mechulicvs.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.Model.MenuList
import com.example.mechulicvs.Repository.GetRecomRepository
import com.example.mechulicvs.Repository.ResultRepository

class GetRecomViewModel(application: Application) : AndroidViewModel(application) {

    private var resultList = MutableLiveData<List<MenuList>>()

    private val context = application.applicationContext

    fun getResultRepository(): LiveData<List<MenuList>>? {
        return resultList
    }

    init {
        resultList = GetRecomRepository.getResult()!!
    }

}