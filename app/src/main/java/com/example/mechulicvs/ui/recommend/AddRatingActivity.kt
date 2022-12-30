package com.example.mechulicvs.ui.recommend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechulicvs.MainApplication
import com.example.mechulicvs.data.remote.model.MenuList
import com.example.mechulicvs.R
import com.example.mechulicvs.viewmodel.recommend.GetRatingListViewModel
import com.example.mechulicvs.databinding.ActivityAddRatingBinding
import java.net.URLEncoder

class AddRatingActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddRatingBinding
    lateinit var getRatingListViewmodel : GetRatingListViewModel

    lateinit var itemAdapter : AddRatingAdapter

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

                    itemAdapter = AddRatingAdapter(this, it)
                    binding.itemListRv.adapter = itemAdapter
                    val layoutManager = LinearLayoutManager(this)
                    binding.itemListRv.layoutManager = layoutManager
                    binding.itemListRv.setHasFixedSize(true)
                    val decoration = DividerItemDecoration(binding.itemListRv.context, LinearLayoutManager(this).orientation)
                    binding.itemListRv.addItemDecoration(decoration)

                    itemAdapter.setOnItemClickListener(object : AddRatingAdapter.OnItemClickListener{
                        override fun onItemClick(v: View, data: MenuList, pos: Int) {
                            val intent = Intent(this@AddRatingActivity, DetailAddRatingActivity::class.java).apply {
                                putExtra("itemId", data.menu_id)
                            }
                            startActivity(intent)
                        }
                    })

                }
            })

        }



    }



}
