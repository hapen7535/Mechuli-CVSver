package com.example.mechulicvs.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechulicvs.Model.ItemData
import com.example.mechulicvs.Model.MenuList
import com.example.mechulicvs.R
import com.example.mechulicvs.View.GetRecomAdapter
import com.example.mechulicvs.ViewModel.GetRecomViewModel
import com.example.mechulicvs.databinding.ActivityGetRecomBinding

class GetRecomActivity : AppCompatActivity() {

    lateinit var binding : ActivityGetRecomBinding
    lateinit var getRecomViewModel: GetRecomViewModel
    var dataList = listOf<MenuList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_recom)

        binding = ActivityGetRecomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getRecomViewModel = ViewModelProvider(this).get(GetRecomViewModel::class.java)

        val observer = Observer<List<MenuList>>{ list ->
            dataList = list
        }

        getRecomViewModel.getResultRepository()!!.observe(this, observer)


        val itemAdapter = GetRecomAdapter(this, dataList)
        binding.listofrecommendRv.adapter = itemAdapter

        val layoutManager = LinearLayoutManager(this)
        binding.listofrecommendRv.layoutManager = layoutManager
        binding.listofrecommendRv.setHasFixedSize(true)
    }
}