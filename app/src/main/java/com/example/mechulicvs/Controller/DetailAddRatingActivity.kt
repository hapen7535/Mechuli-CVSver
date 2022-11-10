package com.example.mechulicvs.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import coil.transform.CircleCropTransformation
import com.example.mechulicvs.R
import com.example.mechulicvs.databinding.ActivityDetailAddRatingBinding

class DetailAddRatingActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailAddRatingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_add_rating)

        binding = ActivityDetailAddRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemId = intent.getIntExtra("itemId", 0)

//        val itemName = intent.getStringExtra("itemName")
//        val itemImg = intent.getStringExtra("itemImg")
//        val storeName = intent.getStringExtra("storeName")

//        binding.ratingSampleIv.load(itemImg) {
//            transformations(CircleCropTransformation())
//        }
//
//        binding.itemNameTv.setText(itemName)
//        binding.itemBrandNameTv.setText(storeName)

    }
}