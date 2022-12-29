package com.example.mechulicvs.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.model.Recipeinfo
import com.example.mechulicvs.model.UserDataAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeListRepository {

    companion object {
        fun getResult() : MutableLiveData<List<Recipeinfo>> {

            val RecomListLiveData : MutableLiveData<List<Recipeinfo>> = MutableLiveData<List<Recipeinfo>>()

            CoroutineScope(Dispatchers.Default).launch {
                launch(Dispatchers.IO) {
                    var response = UserDataAPI.getAllRecipeService.getAllRecipes()
                    withContext(Dispatchers.Default){
                        response.let {
                            if(response.isSuccess){
                                RecomListLiveData.postValue(response.result)
                                Log.d("result", response.result.toString())
                            } else{
                                Log.d("error", response.message)
                            }
                        }
                    }
                }
            }
            return RecomListLiveData
        }


    }

}