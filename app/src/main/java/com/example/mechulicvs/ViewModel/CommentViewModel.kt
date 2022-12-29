package com.example.mechulicvs.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mechulicvs.model.ApiState
import com.example.mechulicvs.model.CommentData
import com.example.mechulicvs.model.CommentRequest
import com.example.mechulicvs.Repository.CommentRepository
import kotlinx.coroutines.launch

class CommentViewModel(application: Application) : AndroidViewModel(application) {
    val commentRepo = CommentRepository()
    val commentResult: MutableLiveData<ApiState<CommentData>> = MutableLiveData()

    fun commentUser(userId: String, recipeId: Int, content: String, rating: Double) {
        commentResult.value = ApiState.Loading()
        viewModelScope.launch {
            try {
                val commentRequest = CommentRequest(userId, recipeId, content, rating)
                val response = commentRepo.userComment(commentRequest = commentRequest)
                Log.d("code", response.code().toString())
                if (response.code() == 200) {
                    commentResult.value = ApiState.Success(response.body())
                } else {
                    commentResult.value = ApiState.Error(response.message())
                }
            } catch (e: Exception) {
                commentResult.value = ApiState.Error(e.message)
            }
        }
    }
}