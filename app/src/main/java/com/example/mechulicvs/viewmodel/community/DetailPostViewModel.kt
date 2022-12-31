package com.example.mechulicvs.viewmodel.community

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mechulicvs.MainApplication
import com.example.mechulicvs.data.remote.model.PostDetail
import com.example.mechulicvs.data.remote.model.PostDetailData
import com.example.mechulicvs.repository.community.DetailPostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//class DetailPostViewModel constructor(
//    application: Application,
////    private val repository: DetailPostRepository
//) : ViewModel() {
//
//    private var resultList = MutableLiveData<PostDetail>()
//
//    fun getResultRepository(): LiveData<PostDetail> {
//        return resultList
//    }
//
//    init {
//        resultList = DetailPostRepository().getResult()
//    }
//
//}

class DetailPostViewModel constructor(
    detailPostRepository: DetailPostRepository
): ViewModel(){

    val recipeId = MainApplication.prefs.getInt("recipeId", 0)

    private val _postInfo = MutableLiveData<PostDetailData>()
    val postInfo = _postInfo as LiveData<PostDetailData>

    init {
        viewModelScope.launch {
            try{
                val postInfo = detailPostRepository.getDetailPostInfo(recipeId)
                _postInfo.value = postInfo
            } catch (error : Exception){
                //정보 받기 에러 창을 사용자에게 보여준다
            }
        }
    }

}