package com.example.mechulicvs.ui.viewmodel.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mechulicvs.data.remote.Resource
import com.example.mechulicvs.data.remote.model.PostDetailData
import com.example.mechulicvs.repository.community.DetailPostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(
    private val detailPostRepository: DetailPostRepository
): ViewModel(){
//    private val recipeId = MainApplication.prefs.getInt("recipeId", 0)

    private val _postInfo = MutableLiveData<Resource<PostDetailData>>()

    val postInfo : LiveData<Resource<PostDetailData>>
        get() = _postInfo

    init {
        getDetailPostInfo()
    }

    private fun getDetailPostInfo() = viewModelScope.launch {
        _postInfo.postValue(Resource.loading(null))
        detailPostRepository.getDetailPost().let {
            if(it.isSuccessful){
                _postInfo.postValue(Resource.success(it.body()))
            }
            else{
                _postInfo.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}