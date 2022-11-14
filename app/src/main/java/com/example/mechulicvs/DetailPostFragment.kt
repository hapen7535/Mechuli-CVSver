package com.example.mechulicvs

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.mechulicvs.Model.Recipeinfo
import com.example.mechulicvs.View.CommunityActivity
import com.example.mechulicvs.View.DetailPostImgVPAdapter
import com.example.mechulicvs.View.RecipeListAdapter
import com.example.mechulicvs.ViewModel.DetailPostViewModel
import com.example.mechulicvs.ViewModel.RecipeListViewModel
import com.example.mechulicvs.databinding.FragmentCommunityMainBinding
import com.example.mechulicvs.databinding.FragmentDetailPostBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailPostFragment : Fragment() {

    lateinit var detailPostViewModel: DetailPostViewModel
    lateinit var communityActivity : CommunityActivity
    lateinit var detailPostImgVPAdapter: DetailPostImgVPAdapter

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
        var fragmentDetailPostBinding : FragmentDetailPostBinding? = null
        val binding = FragmentDetailPostBinding.bind(view)
        fragmentDetailPostBinding = binding

        val postImgsVP = binding.recipeImagesVp
        val postImgsTL = binding.viewpagerIndicatorTb

        detailPostViewModel = ViewModelProvider(this)[DetailPostViewModel::class.java]
        detailPostViewModel.getResultRepository().observe(communityActivity, Observer {
            binding.recipeTitleTv.text = it.recipeTitle
            binding.recipeDateTv.text = it.createTime
            binding.commentCountTv.text = it.replyCount.toString()
            binding.ratingCountTv.text = it.AvgScore.toString()
            binding.recipeIngrTv.text = it.recipeIngr
            binding.recipeCostTv.text = it.recipeCost.toString()

            val imagesList = mutableListOf<String>()
            imagesList.add(it.recipeImg1); imagesList.add(it.recipeImg2); imagesList.add(it.recipeImg3); imagesList.add(it.recipeImg4); imagesList.add(it.recipeImg5);

            detailPostImgVPAdapter = DetailPostImgVPAdapter(communityActivity, imagesList)
            postImgsVP.adapter = detailPostImgVPAdapter
            TabLayoutMediator(postImgsTL, postImgsVP) { tab, position ->

            }.attach()

            binding.recipeContentsTv.text = it.recipeCont
            binding.commentDetailCountTv.text = it.replyCount.toString()


        })
    }

}