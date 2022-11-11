package com.example.mechulicvs.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.Model.MenuList
import com.example.mechulicvs.Model.Recipeinfo
import com.example.mechulicvs.Repository.RecipeListRepository
import com.example.mechulicvs.Repository.ResultRepository

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