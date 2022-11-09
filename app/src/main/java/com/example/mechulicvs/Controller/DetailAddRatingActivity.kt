package com.example.mechulicvs.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechulicvs.R
import com.example.mechulicvs.databinding.ActivityDetailAddRatingBinding

class DetailAddRatingActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailAddRatingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_add_rating)

        binding = ActivityDetailAddRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemName = intent.getStringExtra("itemName")
        val itemImg = intent.getStringExtra("itemImg")
        val storeName = intent.getStringExtra("storeName")

    }
}