package com.example.mechulicvs.ui.community

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.viewpager2.widget.ViewPager2
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
import com.example.mechulicvs.ui.viewmodel.community.RecipeCreateViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
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

    private val detailPostViewModel: DetailPostViewModel by viewModels()
    private var _binding: FragmentDetailPostBinding? = null
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
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_post, container, false)
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
        //다시 메인 커뮤니티로 돌아갈 때 보이게 해야함

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
                            checkIsWriter(postDetailInfo.userNickName, loginNickname, binding.detailIconIv)
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

                            if(checkCommentDuplicated(commentList, loginNickname))binding.commentAddLayout.visibility =
                                View.INVISIBLE

                            setPostImages(imagesList, postImgsTL, postImgsVP)
                            setCommentsPost(commentList, binding.commentsListRv)

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
            commentViewModel = ViewModelProvider(this)[CommentViewModel::class.java]
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

    private fun checkCommentDuplicated(commentList : MutableList<Reply>, loginNickname: String) : Boolean{
        for(i in 0 until commentList.size){
            if(commentList[i].replyNickName.equals(loginNickname)) return true
        }
        return false
    }

    private fun checkIsWriter(userNickname : String, loginNickname : String, postDetailIcon : ImageView){
        if (userNickname != loginNickname) {
            postDetailIcon.visibility = View.INVISIBLE
        }
    }

    private fun setPostImages(imageList: MutableList<String>, postImageFrame : TabLayout, postImageIndicator : ViewPager2){
        detailPostImgVPAdapter =
            DetailPostImgVPAdapter(communityActivity, imageList)
        postImageIndicator.adapter = detailPostImgVPAdapter
        TabLayoutMediator(postImageFrame, postImageIndicator) { tab, position ->
        }.attach()
    }

    private fun setCommentsPost(commentList: MutableList<Reply>, commentsFrame : RecyclerView) {
        detailPostCommentAdapter =
            DetailPostCommentAdapter(communityActivity, commentList)
        commentsFrame.adapter = detailPostCommentAdapter
        val layoutManager = LinearLayoutManager(communityActivity)
        commentsFrame.layoutManager = layoutManager
        commentsFrame.setHasFixedSize(true)
        val decoration = DividerItemDecoration(
            commentsFrame.context,
            LinearLayoutManager(communityActivity).orientation
        )
        commentsFrame.addItemDecoration(decoration)
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