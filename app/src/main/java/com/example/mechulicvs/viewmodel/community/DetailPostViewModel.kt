package com.example.mechulicvs.viewmodel.community

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mechulicvs.data.remote.model.PostDetail
import com.example.mechulicvs.repository.community.DetailPostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class DetailPostViewModel constructor(
    application: Application,
//    private val repository: DetailPostRepository
) : ViewModel() {

    private var resultList = MutableLiveData<PostDetail>()

    fun getResultRepository(): LiveData<PostDetail> {
        return resultList
    }

    init {
        resultList = DetailPostRepository().getResult()
    }

}