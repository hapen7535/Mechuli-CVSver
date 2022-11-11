package com.example.mechulicvs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mechulicvs.ViewModel.RecipeListViewModel
import com.example.mechulicvs.databinding.FragmentCommunityMainBinding

class MainCommunityFragment : Fragment() {

    lateinit var recipeViewModel : RecipeListViewModel
    lateinit var recipeListViewModel: RecipeListViewModel

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_community_main, container, false)
//
//        val binding = FragmentCommunityMainBinding.bind()
//
//    }

    private var fragmentMainComunityBinding : FragmentCommunityMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCommunityMainBinding.bind(view)
        fragmentMainComunityBinding = binding
        binding.postListRv
    }

}