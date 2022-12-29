package com.example.mechulicvs.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mechulicvs.model.PostDetail
import com.example.mechulicvs.Repository.DetailPostRepository

class DetailPostViewModel constructor(
//    application: Application,
    private val repository: DetailPostRepository
) : ViewModel() {

    private var resultList = MutableLiveData<PostDetail>()

    fun getResultRepository(): LiveData<PostDetail> {
        return resultList
    }

    init {
        resultList = repository.getResult()
    }

}