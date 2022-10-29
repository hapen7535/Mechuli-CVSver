package com.example.mechulicvs

import android.app.Application
import androidx.lifecycle.*
import com.example.mechulicvs.Model.Menu
import com.example.mechulicvs.Model.MenuList
import com.example.mechulicvs.Model.UserDataAPI
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private var resultList = MutableLiveData<List<MenuList>>()

    private val context = application.applicationContext

//    var job: Job? = null
//    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
//        onError("Exception: ${throwable.localizedMessage}")
//    }
//
//    val ratingLoadError = MutableLiveData<String?>()
//    val loading = MutableLiveData<Boolean>()

    fun getResultRepository(): LiveData<List<MenuList>>? {
        return resultList
    }

    init {
        resultList = ResultRepository.getResult()!!
    }

}
//    fun getList() {
//        viewModelScope.launch {
//            resultList.value = handlesignUpData()
//        }
//    }
//
//    private suspend fun handlesignUpData() : List<MenuList> =
//
//        suspendCoroutine<String> { continuation ->
//
////            val response = UserDataAPI.signupMenuDataService.getSignupMenuData()
////            withContext(Dispatchers.Main) {
////                if (response.isSuccess) {
////                    continuation.resumeWith(response)
////                } else {
////                    onError("Error : ${response.message}")
////                }
////            }
//
//        }
//
//
//    private fun onError(message: String) {
//        ratingLoadError.value = message
//        loading.value = false
//    }
//
//    override fun onCleared() {
//        job?.cancel()
//    }
//
//}