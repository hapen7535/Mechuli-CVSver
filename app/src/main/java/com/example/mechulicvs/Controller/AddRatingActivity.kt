package com.example.mechulicvs.Controller

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.mechulicvs.MainApplication
import com.example.mechulicvs.Model.MenuList
import com.example.mechulicvs.R
import com.example.mechulicvs.Repository.PrefRepository
import com.example.mechulicvs.View.AddRatingAdapter
import com.example.mechulicvs.View.GetRecomAdapter
import com.example.mechulicvs.ViewModel.GetRatingListViewModel
import com.example.mechulicvs.databinding.ActivityAddRatingBinding
import java.net.URLEncoder
import java.util.*
import java.util.Base64.getEncoder

class AddRatingActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddRatingBinding
    lateinit var getRatingListViewmodel : GetRatingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rating)

        binding = ActivityAddRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
      binding.backpressBtn.setOnClickListener {
           onBackPressedDispatcher.onBackPressed()
       }

        val layoutManager = LinearLayoutManager(this)
        binding.itemListRv.layoutManager = layoutManager
        binding.itemListRv.setHasFixedSize(true)

        binding.searchBtn.setOnClickListener {
            var keyword =  binding.searchViewEt.text.toString()
            keyword = URLEncoder.encode(keyword, "utf-8");
            MainApplication.prefs.setString("keyword", keyword)

            getRatingListViewmodel = ViewModelProvider(this).get(GetRatingListViewModel::class.java)

            getRatingListViewmodel.getResultRepository().observe(this, Observer {
                if (it.isNotEmpty()){

                    val itemAdapter = AddRatingAdapter(this, it)
                    binding.itemListRv.adapter = itemAdapter
                    val layoutManager = LinearLayoutManager(this)
                    binding.itemListRv.layoutManager = layoutManager
                    binding.itemListRv.setHasFixedSize(true)
                    val decoration = DividerItemDecoration(binding.itemListRv.context, LinearLayoutManager(this).orientation)
                    binding.itemListRv.addItemDecoration(decoration)

                }
            })

        }


    }



}
