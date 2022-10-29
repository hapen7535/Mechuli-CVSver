package com.example.mechulicvs.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.Model.MenuList
import com.example.mechulicvs.Model.UserDataAPI.signupMenuDataService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultRepository {

    companion object {
        fun getResult() : MutableLiveData<List<MenuList>>?{

            val signUpListLiveData : MutableLiveData<List<MenuList>> = MutableLiveData<List<MenuList>>()

            CoroutineScope(Dispatchers.Default).launch {
                launch(Dispatchers.IO) {
                    var response = signupMenuDataService.getSignupMenuData()
                    withContext(Dispatchers.Default){
                        response.let {
                            if(response.isSuccess){
                                signUpListLiveData.postValue(response.result)
                            } else{
                                Log.d("error", response.message)
                            }
                        }
                    }
                }
            }
            return signUpListLiveData
        }
    }

}