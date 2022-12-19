package com.example.mechulicvs.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mechulicvs.Model.PostDetail
import com.example.mechulicvs.Model.PostDetailData
import com.example.mechulicvs.Repository.DetailAddRatingSetRepository
import com.example.mechulicvs.Repository.DetailPostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

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