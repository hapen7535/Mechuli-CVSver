package com.example.mechulicvs.ViewModel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.Model.MenuList
import com.example.mechulicvs.Repository.GetRecomRepository
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetRecomViewModel @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {

    private var resultList = MutableLiveData<List<MenuList>>()

//    fun getLog() {
//        Log.d("viewmodel","viewmodel 진입getString(\"userId\", \"\")!!")
//    }


    fun getResultRepository(): LiveData<List<MenuList>> {
//        getLog()
        return resultList
    }

//    fun getLoadingState() : LiveData<Boolean> {
//        return GetRecomRepository.isLoading
//    }

    init {
        resultList = GetRecomRepository.getResult()!!
    }

}