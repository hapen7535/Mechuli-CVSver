package com.example.mechulicvs.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.Model.Result
import com.example.mechulicvs.Repository.DetailAddRatingRepository
import com.example.mechulicvs.Repository.DetailAddRatingRepository.Companion.getResult

class DetailAddRatingViewModel(application: Application) : AndroidViewModel(application) {

    private var resultList = MutableLiveData<List<Result>>()

    private val context = application.applicationContext

    fun getResultRepository(): LiveData<List<Result>> {
        return resultList
    }

    init {
        resultList = DetailAddRatingRepository.getResult()!!
    }
}