package com.example.mechulicvs.ui.recommend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechulicvs.data.remote.model.MenuList
import com.example.mechulicvs.R
import com.example.mechulicvs.ui.viewmodel.recommend.GetRecomViewModel
import com.example.mechulicvs.databinding.ActivityGetRecomBinding
import com.example.mechulicvs.ui.recommend.adapter.GetRecomAdapter

class GetRecomActivity : AppCompatActivity() {

    lateinit var binding : ActivityGetRecomBinding
    lateinit var getRecomViewModel: GetRecomViewModel
    var dataList = listOf<MenuList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_recom)

        binding = ActivityGetRecomBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.listofrecommendRv.visibility = View.GONE
        binding.progressBar1.visibility = View.VISIBLE

        getRecomViewModel = ViewModelProvider(this).get(GetRecomViewModel::class.java)

//        val observer = Observer<List<MenuList>>{ list ->
//            dataList = list
//        }

        getRecomViewModel.getResultRepository().observe(this, Observer{
            if(it.isNotEmpty()){

                val itemAdapter = GetRecomAdapter(this, it)
                binding.listofrecommendRv.adapter = itemAdapter

                val layoutManager = LinearLayoutManager(this)
                binding.listofrecommendRv.layoutManager = layoutManager
                binding.listofrecommendRv.setHasFixedSize(true)
                val decoration = DividerItemDecoration(binding.listofrecommendRv.context, LinearLayoutManager(this).orientation)
                binding.listofrecommendRv.addItemDecoration(decoration)

                binding.progressBar1.visibility = View.GONE
//                binding.listofrecommendRv.visibility = View.VISIBLE

            }
        })

        binding.backpressBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }
}