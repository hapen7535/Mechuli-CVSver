package com.example.mechulicvs.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechulicvs.Model.ItemData
import com.example.mechulicvs.R
import com.example.mechulicvs.View.GetRecomAdapter
import com.example.mechulicvs.databinding.ActivityGetRecomBinding

class GetRecomActivity : AppCompatActivity() {

    lateinit var binding : ActivityGetRecomBinding
    var dataList = mutableListOf<ItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_recom)

        binding = ActivityGetRecomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemAdapter = GetRecomAdapter(this, dataList)
        binding.listofrecommendRv.adapter = itemAdapter

        val layoutManager = LinearLayoutManager(this)
        binding.listofrecommendRv.layoutManager = layoutManager
        binding.listofrecommendRv.setHasFixedSize(true)
    }
}