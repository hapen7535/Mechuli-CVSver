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
import com.example.mechulicvs.Repository.GetRatingListRepository
import com.example.mechulicvs.Repository.GetRecomRepository
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetRatingListViewModel @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {

    private var resultList = MutableLiveData<List<MenuList>>()

    fun getLog() {
        Log.d("getRating viewmodel","getRating 진입")
    }


    fun getResultRepository(): LiveData<List<MenuList>> {
        getLog()
        return resultList
    }

    init {
        resultList = GetRatingListRepository.getResult()!!
    }

}