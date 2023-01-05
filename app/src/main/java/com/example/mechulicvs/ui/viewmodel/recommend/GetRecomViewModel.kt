package com.example.mechulicvs.ui.viewmodel.recommend

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.data.remote.model.MenuList
import com.example.mechulicvs.repository.recommend.GetRecomRepository
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