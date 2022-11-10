package com.example.mechulicvs.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.example.mechulicvs.MainApplication
import com.example.mechulicvs.Model.MenuList
import com.example.mechulicvs.Model.Result
import com.example.mechulicvs.R
import com.example.mechulicvs.ViewModel.DetailAddRatingViewModel
import com.example.mechulicvs.ViewModel.GetRatingListViewModel
import com.example.mechulicvs.databinding.ActivityDetailAddRatingBinding

class DetailAddRatingActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailAddRatingBinding
    lateinit var viewModel: DetailAddRatingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_add_rating)

        binding = ActivityDetailAddRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemId = intent.getIntExtra("itemId", 0)
        MainApplication.prefs.setInt("itemId", itemId)

        viewModel = ViewModelProvider(this).get(DetailAddRatingViewModel::class.java)

        val observer = Observer<Result> { it ->

            binding.ratingSampleIv.load(it.menuImg) {
                transformations(CircleCropTransformation())
            }

            binding.itemBrandNameTv.setText(it.cvsName)
            binding.itemNameTv.setText(it.menuName)

        }

        viewModel.getResultRepository().observe(this, observer)


    }
}