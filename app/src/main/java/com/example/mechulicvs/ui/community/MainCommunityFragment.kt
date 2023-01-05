package com.example.mechulicvs.ui.community

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechulicvs.MainApplication
import com.example.mechulicvs.R
import com.example.mechulicvs.data.remote.model.Recipeinfo
import com.example.mechulicvs.ui.viewmodel.community.RecipeListViewModel
import com.example.mechulicvs.databinding.FragmentCommunityMainBinding

class MainCommunityFragment : Fragment() {

    lateinit var recipeViewModel : RecipeListViewModel
    lateinit var communityActivity : CommunityActivity
    lateinit var recipeListAdapter: RecipeListAdapter
//    lateinit var recipeListRecyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communityActivity = context as CommunityActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_community_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var fragmentMainComunityBinding : FragmentCommunityMainBinding? = null

        val binding = FragmentCommunityMainBinding.bind(view)
        fragmentMainComunityBinding = binding

        recipeViewModel = ViewModelProvider(this)[RecipeListViewModel::class.java]

        recipeViewModel.getResultRepository()?.observe(communityActivity, Observer {
            if(it.isNotEmpty()){
                recipeListAdapter = RecipeListAdapter(communityActivity, it,
                    object : RecipeListAdapter.OnItemClickListener{
                        override fun onItemClick(v: Recipeinfo, pos: Int) {
                            MainApplication.prefs.setInt("recipeId", it[pos].recipeId)
                            communityActivity.changeToDetail()
                        }
                    })
                binding.postListRv.adapter = recipeListAdapter
                val layoutManager = LinearLayoutManager(communityActivity)
                binding.postListRv.layoutManager = layoutManager
                binding.postListRv.setHasFixedSize(true)
                val decoration = DividerItemDecoration(binding.postListRv.context, LinearLayoutManager(communityActivity).orientation)
                binding.postListRv.addItemDecoration(decoration)

            }
        })
    }

}