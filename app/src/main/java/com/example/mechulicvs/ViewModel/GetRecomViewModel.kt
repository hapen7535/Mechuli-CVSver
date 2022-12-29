package com.example.mechulicvs.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.model.MenuList
import com.example.mechulicvs.Repository.GetRecomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetRecomViewModel @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {

    private var resultList = MutableLiveData<List<MenuList>>()

    fun getResultRepository(): LiveData<List<MenuList>> {
        return resultList
    }

    init {
        resultList = GetRecomRepository.getResult()!!
    }

}