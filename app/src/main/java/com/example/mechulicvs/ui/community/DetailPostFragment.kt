package com.example.mechulicvs.ui.community

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechulicvs.di.MainApplication
import com.example.mechulicvs.R
import com.example.mechulicvs.data.*
import com.example.mechulicvs.data.remote.Status
import com.example.mechulicvs.ui.viewmodel.community.CommentViewModel
import com.example.mechulicvs.data.remote.model.Reply
import com.example.mechulicvs.databinding.FragmentDetailPostBinding
import com.example.mechulicvs.ui.community.BottomSheetFragment.Companion.TAG
import com.example.mechulicvs.ui.community.adapter.DetailPostCommentAdapter
import com.example.mechulicvs.ui.community.adapter.DetailPostImgVPAdapter
import com.example.mechulicvs.ui.viewmodel.community.DetailPostViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPostFragment : Fragment() {

    lateinit var communityActivity: CommunityActivity
    lateinit var detailPostImgVPAdapter: DetailPostImgVPAdapter
    lateinit var detailPostCommentAdapter: DetailPostCommentAdapter
    private lateinit var commentViewModel: CommentViewModel

    var commentList = mutableListOf<Reply>()
    val postingBottomSheet = BottomSheetFragment()

    private val detailPostViewModel : DetailPostViewModel by viewModels()
    private var _binding : FragmentDetailPostBinding? = null
    private val binding get() = _binding!!


    override fun onAttach(context: Context) {
        super.onAttach(context)
        communityActivity = context as CommunityActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_post, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.postViewModel = detailPostViewModel
        val postImgsVP = binding.recipeImagesVp
        val postImgsTL = binding.viewpagerIndicatorTb

        var commentRating = 0.0

//        postingBottomSheet.show(childFragmentManager, BottomSheetFragment.TAG)

        val fab = activity?.findViewById<FloatingActionButton>(R.id.write_post_btn)
        fab?.visibility = View.GONE
        //?????? ?????? ??????????????? ????????? ??? ????????? ?????????

        val loginNickname = MainApplication.prefs.getString("userNickname", "")
        binding.commentNickNameAddTv.text = loginNickname

        detailPostViewModel.postInfo.observe(communityActivity, Observer {
            when (it.status) {
                Status.ERROR -> {
                    Log.d("ERROR", it.message.toString())
                }
                Status.SUCCESS -> {
                    it.data.let { res ->
                        val postDetailInfo = res?.result
                        if (postDetailInfo != null) {
                            if (postDetailInfo.userNickName != loginNickname) binding.detailIconIv.visibility =
                                View.INVISIBLE
                            val imagesList = setImageList(
                                postDetailInfo.recipeImg1,
                                postDetailInfo.recipeImg2,
                                postDetailInfo.recipeImg3,
                                postDetailInfo.recipeImg4,
                                postDetailInfo.recipeImg5
                            )
                            for (i in 0 until postDetailInfo.replyCount) {
                                commentList.add(postDetailInfo.replyList[i])
                            }

                            detailPostImgVPAdapter =
                                DetailPostImgVPAdapter(communityActivity, imagesList)
                            postImgsVP.adapter = detailPostImgVPAdapter
                            TabLayoutMediator(postImgsTL, postImgsVP) { tab, position ->
                            }.attach()

                            detailPostCommentAdapter =
                                DetailPostCommentAdapter(communityActivity, commentList)

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
                        }
                    }
                }
            }
        })

        binding.commentRatingAddRb.setOnRatingBarChangeListener { _, rating, _ ->
            commentRating = rating.toDouble()
        }

        binding.addCommentBtn.setOnClickListener {
            val inputComment = getChangedText(binding.inputCommentEt.text)
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

                    }
                    is ApiState.Error -> {
                        Log.d("Error", it.msg.toString())
                    }
                }
            })

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setImageList(
        imageUrl1: String,
        imageUrl2: String,
        imageUrl3: String,
        imageUrl4: String,
        imageUrl5: String,
    ): MutableList<String> {
        val imageList = mutableListOf<String>()
        imageList.add(imageUrl1)
        imageList.add(imageUrl2)
        imageList.add(imageUrl3)
        imageList.add(imageUrl4)
        imageList.add(imageUrl5)

        return imageList
    }

    private fun getChangedText(inputComment: Editable): String {
        var comment = ""
        if (inputComment.isNotEmpty()) {
            comment = inputComment.toString()
            return comment
        }
        return comment
    }

}