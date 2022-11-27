package com.example.mechulicvs.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mechulicvs.Model.CommentData
import com.example.mechulicvs.Repository.CommentRepository

class CommentViewModel(application: Application) : AndroidViewModel(application){
    val commentRepo = CommentRepository()
    val commentResult : MutableLiveData<BaseResponse<CommentData>> = MutableLiveData()

    fun commentUser(userId : String, recipeId : Int, content : String, rating : Double){
        commentResult.value =
    }

}