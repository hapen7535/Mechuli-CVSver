package com.example.mechulicvs.ui.community

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechulicvs.MainApplication
import com.example.mechulicvs.R
import com.example.mechulicvs.data.*
import com.example.mechulicvs.data.remote.model.detailpost.PostElements
import com.example.mechulicvs.viewmodel.community.CommentViewModel
import com.example.mechulicvs.viewmodel.community.DetailPostViewModel
import com.example.mechulicvs.data.remote.model.Reply
import com.example.mechulicvs.databinding.FragmentDetailPostBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class DetailPostFragment : Fragment() {

//    lateinit var detailPostViewModel: DetailPostViewModel
    private val detailPostViewModel by viewModels<DetailPostViewModel>()

    lateinit var communityActivity: CommunityActivity
    lateinit var detailPostImgVPAdapter: DetailPostImgVPAdapter
    lateinit var detailPostCommentAdapter: DetailPostCommentAdapter
    private lateinit var commentViewModel: CommentViewModel

//    private val commentViewModel by viewModels<CommentViewModel>() //by viewModels로 ViewModel을 지연 생성

    var commentList = mutableListOf<Reply>()

    val postingBottomSheet = BottomSheetFragment()


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

        postingBottomSheet.show(childFragmentManager , BottomSheetFragment.TAG)

        val fab = activity?.findViewById<FloatingActionButton>(R.id.write_post_btn)
        fab?.visibility = View.GONE
        //다시 메인 커뮤니티로 돌아갈 때 보이게 해야함

        val loginNickname = MainApplication.prefs.getString("userNickname", "")

        binding.commentNickNameAddTv.text = loginNickname


//        detailPostViewModel = ViewModelProvider(this)[DetailPostViewModel::class.java]
//        detailPostViewModel.getResultRepository().observe(communityActivity, Observer {
        detailPostViewModel.postInfo.observe(communityActivity, Observer {
            val postDetailInfo = it.result
//            binding.post = PostElements(postDetailInfo.recipeTitle, postDetailInfo.createTime, postDetailInfo.replyCount.toString(), postDetailInfo.AvgScore.toString(), postDetailInfo.recipeIngr, postDetailInfo.recipeCost.toString(), postDetailInfo.userNickName)
            if(postDetailInfo.userNickName != loginNickname) binding.detailIconIv.visibility = View.INVISIBLE
            val imagesList = mutableListOf<String>()
            imagesList.add(postDetailInfo.recipeImg1); imagesList.add(postDetailInfo.recipeImg2); imagesList.add(postDetailInfo.recipeImg3); imagesList.add(postDetailInfo.recipeImg4); imagesList.add(postDetailInfo.recipeImg5);
            for (i in 0 until postDetailInfo.replyCount) {
                commentList.add(postDetailInfo.replyList[i])
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

            binding.recipeContentsTv.text = postDetailInfo.recipeCont
            binding.commentDetailCountTv.text = postDetailInfo.replyCount.toString()

        })
//            binding.post= PostElements(it.recipeTitle, it.createTime, it.replyCount.toString(), it.AvgScore.toString(), it.recipeIngr, it.recipeCost.toString(), it.userNickName)
//
//            if (it.userNickName != loginNickname) {
//                binding.detailIconIv.visibility = View.INVISIBLE
//            }
//
//            val imagesList = mutableListOf<String>()
//            imagesList.add(it.recipeImg1); imagesList.add(it.recipeImg2); imagesList.add(it.recipeImg3); imagesList.add(it.recipeImg4); imagesList.add(it.recipeImg5);
//
//            for (i in 0 until it.replyCount) {
//                commentList.add(it.replyList[i])
//            }
//
//            detailPostImgVPAdapter = DetailPostImgVPAdapter(communityActivity, imagesList)
//            postImgsVP.adapter = detailPostImgVPAdapter
//            TabLayoutMediator(postImgsTL, postImgsVP) { tab, position ->
//            }.attach()
//
//            detailPostCommentAdapter = DetailPostCommentAdapter(communityActivity, commentList)
//
//            binding.commentsListRv.adapter = detailPostCommentAdapter
//            val layoutManager = LinearLayoutManager(communityActivity)
//            binding.commentsListRv.layoutManager = layoutManager
//            binding.commentsListRv.setHasFixedSize(true)
//            val decoration = DividerItemDecoration(
//                binding.commentsListRv.context,
//                LinearLayoutManager(communityActivity).orientation
//            )
//            binding.commentsListRv.addItemDecoration(decoration)
//
//            binding.recipeContentsTv.text = it.recipeCont
//            binding.commentDetailCountTv.text = it.replyCount.toString()
//
//
//        })


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
                        it.data?.let { it1 -> commentList.plusAssign(it1.result) }
                        detailPostCommentAdapter.notifyDataSetChanged()
//                        Log.d("commentCount", commentList.size.toString())
//                        Log.d("ratingCount", ((binding.ratingCountTv.text.toString()
//                            .toInt() * binding.commentDetailCountTv.text.toString().toInt()
//                                + it.data!!.result.replyScore) / commentList.size).toString())

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

    fun getChangedText(inputComment: Editable): String {
        var comment = ""
        if (inputComment.isNotEmpty()) {
            comment = inputComment.toString()
            return comment
        }
        return comment
    }

}