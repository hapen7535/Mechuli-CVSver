package com.example.mechulicvs.ViewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.mechulicvs.model.MenuList
import com.example.mechulicvs.Repository.ResultRepository

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private var resultList = MutableLiveData<List<MenuList>>()

    private val context = application.applicationContext

    fun getResultRepository(): LiveData<List<MenuList>>? {
        return resultList
    }

    init {
        resultList = ResultRepository.getResult()!!
    }

}
