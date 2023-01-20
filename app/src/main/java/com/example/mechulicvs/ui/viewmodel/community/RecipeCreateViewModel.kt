package com.example.mechulicvs.ui.viewmodel.community

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mechulicvs.data.ApiState
import com.example.mechulicvs.data.remote.model.detailpost.Recipe
import com.example.mechulicvs.data.remote.model.detailpost.RecipeRequest
import com.example.mechulicvs.repository.community.RecipeRepository
import kotlinx.coroutines.launch

class RecipeCreateViewModel(application: Application) : AndroidViewModel(application) {

    val recipeCreateRepo = RecipeRepository()
    val recipeResult : MutableLiveData<ApiState<Recipe>> = MutableLiveData()

    fun getRecipeCreateResult(recipeContent : RecipeRequest){
        recipeResult.value = ApiState.Loading()
        viewModelScope.launch {
            try {
                val recipeRequest = RecipeRequest(
                    id = recipeContent.id,
                    recipeTitle = recipeContent.recipeTitle,
                    recipeIngr = recipeContent.recipeIngr,
                    recipeCost = recipeContent.recipeCost,
                    recipeContent = recipeContent.recipeContent,
                    recipeImgUrl1 = recipeContent.recipeImgUrl1,
                    recipeImgUrl2 = recipeContent.recipeImgUrl2,
                    recipeImgUrl3 = recipeContent.recipeImgUrl3,
                    recipeImgUrl4 = recipeContent.recipeImgUrl4,
                    recipeImgUrl5 = recipeContent.recipeImgUrl5
                )
                val response = recipeCreateRepo.recipeContent(recipeRequest = recipeRequest)
//                Log.d("code", response.code().toString())
                if (response.code() == 200) {
                    recipeResult.value = ApiState.Success(response.body())
                } else {
                    recipeResult.value = ApiState.Error(response.message())
                }
            } catch (e: Exception) {
                recipeResult.value = ApiState.Error(e.message)
            }
        }
    }

}