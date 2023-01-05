package com.example.mechulicvs.ui.viewmodel.community

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.data.remote.model.Recipeinfo
import com.example.mechulicvs.repository.community.RecipeListRepository

class RecipeListViewModel(application: Application) : AndroidViewModel(application) {

    private var resultList = MutableLiveData<List<Recipeinfo>>()

    private val context = application.applicationContext

    fun getResultRepository(): LiveData<List<Recipeinfo>>? {
        return resultList
    }

    init {
        resultList = RecipeListRepository.getResult()!!
    }

}