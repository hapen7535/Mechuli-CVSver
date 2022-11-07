package com.example.mechulicvs.Controller

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
    var dataList = listOf<MenuList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rating)

        binding = ActivityAddRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
      binding.backpressBtn.setOnClickListener {
           onBackPressedDispatcher.onBackPressed()
       }

        binding.itemListRv.visibility = View.GONE

        val itemAdapter = AddRatingAdapter(dataList, binding)
        binding.itemListRv.adapter = itemAdapter

        val layoutManager = LinearLayoutManager(this)
        binding.itemListRv.layoutManager = layoutManager
        binding.itemListRv.setHasFixedSize(true)

        binding.searchBtn.setOnClickListener {
            var keyword =  binding.searchViewEt.text.toString()
            keyword = URLEncoder.encode(keyword, "utf-8");
            //sharedPreference에 keyword를 저장, API에서 getString하여 header에 추가
//             val byte = keyword.toByteArray(charset("UTF-8"))
//             val encodedString: String = Base64.getEncoder().encodeToString(byte)
            MainApplication.prefs.setString("keyword", keyword)
            getRatingListViewmodel = ViewModelProvider(this).get(GetRatingListViewModel::class.java)

            val observer = Observer<List<MenuList>>{ list ->
                dataList = list
            }

            getRatingListViewmodel.getResultRepository().observe(this, observer)

            binding.itemListRv.visibility = View.VISIBLE
        }


    }



}
