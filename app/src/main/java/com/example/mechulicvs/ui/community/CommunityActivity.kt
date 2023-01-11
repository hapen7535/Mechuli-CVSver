package com.example.mechulicvs.ui.community

import android.os.Bundle
import android.view.SurfaceControl.Transaction
import androidx.appcompat.app.AppCompatActivity
import com.example.mechulicvs.R
import com.example.mechulicvs.databinding.ActivityCommunityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityActivity : AppCompatActivity() {

    lateinit var binding: ActivityCommunityBinding
    private val detailFragment = DetailPostFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        binding = ActivityCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadRecipeList()

        binding.writePostBtn.setOnClickListener {
            changeToWriteRecipe()
        }
    }

    private fun changeToWriteRecipe(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(
            R.id.community_fragment_frame,
            WritePostFragment()
        )
        transaction.commit()
    }

    private fun loadRecipeList(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(
            R.id.community_fragment_frame,
            MainCommunityFragment()
        )
        transaction.commit()
    }

    fun changeToDetail() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.community_fragment_frame, detailFragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

}