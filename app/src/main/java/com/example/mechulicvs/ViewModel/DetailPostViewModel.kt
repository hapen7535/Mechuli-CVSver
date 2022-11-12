package com.example.mechulicvs.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.Model.PostDetail
import com.example.mechulicvs.Repository.DetailAddRatingSetRepository
import com.example.mechulicvs.Repository.DetailPostRepository

class DetailPostViewModel(application: Application) : AndroidViewModel(application)  {

    private var resultList = MutableLiveData<PostDetail>()

    private val context = application.applicationContext

    fun getResultRepository(): LiveData<PostDetail> {
        return resultList
    }

    init {
        resultList = DetailPostRepository.getResult()!!
    }

}