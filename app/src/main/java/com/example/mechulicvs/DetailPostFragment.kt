package com.example.mechulicvs

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.mechulicvs.Model.*
import com.example.mechulicvs.View.CommunityActivity
import com.example.mechulicvs.View.DetailPostCommentAdapter
import com.example.mechulicvs.View.DetailPostImgVPAdapter
import com.example.mechulicvs.View.RecipeListAdapter
import com.example.mechulicvs.ViewModel.CommentViewModel
import com.example.mechulicvs.ViewModel.DetailPostViewModel
import com.example.mechulicvs.ViewModel.RecipeListViewModel
import com.example.mechulicvs.databinding.FragmentCommunityMainBinding
import com.example.mechulicvs.databinding.FragmentDetailPostBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.suspendCoroutine

class DetailPostFragment : Fragment() {

    lateinit var detailPostViewModel: DetailPostViewModel
    lateinit var communityActivity: CommunityActivity
    lateinit var detailPostImgVPAdapter: DetailPostImgVPAdapter
    lateinit var detailPostCommentAdapter: DetailPostCommentAdapter

    private val commentViewModel by viewModels<CommentViewModel>() //by viewModels로 ViewModel을 지연 생성

    var commentList = mutableListOf<Reply>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communityActivity = context as CommunityActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_post, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var fragmentDetailPostBinding: FragmentDetailPostBinding? = null
        val binding = FragmentDetailPostBinding.bind(view)
        fragmentDetailPostBinding = binding

        val postImgsVP = binding.recipeImagesVp
        val postImgsTL = binding.viewpagerIndicatorTb

        var commentRating = 0.0


        val fab = activity?.findViewById<FloatingActionButton>(R.id.write_post_btn)
        fab?.visibility = View.GONE
        //다시 메인 커뮤니티로 돌아갈 때 보이게 해야함

        val loginNickname = MainApplication.prefs.getString("userNickname", "")

        binding.commentNickNameAddTv.text = loginNickname


        detailPostViewModel = ViewModelProvider(this)[DetailPostViewModel::class.java]
        detailPostViewModel.getResultRepository().observe(communityActivity, Observer {
            binding.recipeTitleTv.text = it.recipeTitle
            binding.recipeDateTv.text = it.createTime
            binding.commentCountTv.text = it.replyCount.toString()
            binding.ratingCountTv.text = it.AvgScore.toString()
            binding.recipeIngrTv.text = it.recipeIngr
            binding.recipeCostTv.text = it.recipeCost.toString()
            binding.nickNameTv.text = it.userNickName

            if (it.userNickName != loginNickname) {
                binding.detailIconIv.visibility = View.INVISIBLE
            }

            val imagesList = mutableListOf<String>()
            imagesList.add(it.recipeImg1); imagesList.add(it.recipeImg2); imagesList.add(it.recipeImg3); imagesList.add(
            it.recipeImg4
        ); imagesList.add(it.recipeImg5);

            for (i in 0 until it.replyCount) {
                commentList.add(it.replyList[i])
            }

            detailPostImgVPAdapter = DetailPostImgVPAdapter(communityActivity, imagesList)
            postImgsVP.adapter = detailPostImgVPAdapter
            TabLayoutMediator(postImgsTL, postImgsVP) { tab, position ->

            }.attach()

            detailPostCommentAdapter = DetailPostCommentAdapter(communityActivity, commentList)

            binding.commentsListRv.adapter = detailPostCommentAdapter
            val layoutManager = LinearLayoutManager(communityActivity)
            binding.commentsListRv.layoutManager = layoutManager
            binding.commentsListRv.setHasFixedSize(true)
            val decoration = DividerItemDecoration(
                binding.commentsListRv.context,
                LinearLayoutManager(communityActivity).orientation
            )
            binding.commentsListRv.addItemDecoration(decoration)

            binding.recipeContentsTv.text = it.recipeCont
            binding.commentDetailCountTv.text = it.replyCount.toString()


        })


        binding.commentRatingAddRb.setOnRatingBarChangeListener { _, rating, _ ->
            commentRating = rating.toDouble()
        }

        binding.addCommentBtn.setOnClickListener {
            val inputComment = getChangedText(binding.inputCommentEt.text)
            Log.d("inputcomment", inputComment)
            val recipeId = MainApplication.prefs.getInt("recipeId", 0)
            val userId = MainApplication.prefs.getString("userId", "")
            commentViewModel.commentUser(userId, recipeId, inputComment, commentRating)

            commentViewModel.commentResult.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is ApiState.Loading -> {
                        Log.d("Loading", it.toString())
                    }
                    is ApiState.Success -> {
                        Log.d("Success", "댓글 등록 성공")
                        Log.d("commentList", detailPostCommentAdapter.getRVItemList().toString())
//                        val currentComments: MutableList<Reply> =
//                            detailPostCommentAdapter.getRVItemList() as MutableList<Reply>
//                        currentComments.add(it.data)
                        it.data?.let { it1 -> commentList.plusAssign(it1.result) }
                        detailPostCommentAdapter.notifyDataSetChanged()
//                        val ft = parentFragmentManager.beginTransaction()
//                        ft.detach(this).attach(this).commit()
                    }
                    is ApiState.Error -> {
                        Log.d("Error", it.msg.toString())
                    }
                }
            })

        }

    }

    override fun onResume() {
        super.onResume()

    }

//    fun sendComment(content : String, rating : Double) : MutableLiveData<Reply> {
//        val userId = MainApplication.prefs.getString("userId", "")
//        val recipeId = MainApplication.prefs.getInt("recipeId", 0)
//
//        val result : MutableLiveData<Reply>
//
//
//        lifecycleScope.launch {
//            val res = withContext(Dispatchers.IO) {
//                UserDataAPI.sendCommentDataService.sendComment(userId, recipeId, content, rating)
//            }
//            if (res.isSuccess) {
//                Log.d("commentResult", res.message)
//
//            }
//        }
//    }

//    fun sendCommentResult(content : String, rating : Double){
//
//    }


//    fun sendAddedComment(content : String, score : Double) : Boolean {
//        val userId = MainApplication.prefs.getString("userId", "")
//        val recipeId = MainApplication.prefs.getInt("recipeId", 0)
//        var result = false
//
//        lifecycleScope.launch {
//            val res = withContext(Dispatchers.IO) {
//                UserDataAPI.sendCommentDataService.sendComment(userId, recipeId, content, score)
//            }
//            if (res.isSuccess) {
//                result = true
//            }
//        }
//        return result
//    }

    fun getChangedText(inputComment: Editable): String {
        var comment = ""
        if (inputComment.isNotEmpty()) {
            comment = inputComment.toString()
            return comment
        }
        return comment
    }

}