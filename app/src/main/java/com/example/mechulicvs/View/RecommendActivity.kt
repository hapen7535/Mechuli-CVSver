package com.example.mechulicvs.View//package com.example.mechulicvs.

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechulicvs.AddRatingActivity
import com.example.mechulicvs.Controller.GetRecomActivity
import com.example.mechulicvs.R
import com.example.mechulicvs.databinding.ActivityRecommendBinding

class RecommendActivity : AppCompatActivity() {

    lateinit var binding : ActivityRecommendBinding

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend)

       binding = ActivityRecommendBinding.inflate(layoutInflater)
       setContentView(binding.root)

       binding.backpressBtn.setOnClickListener {
           onBackPressedDispatcher.onBackPressed()
       }

       binding.addBtn.setOnClickListener {
           val intent = Intent(this, AddRatingActivity::class.java)
           startActivity(intent)
       }

       binding.recommendBtn.setOnClickListener {
           val intent = Intent(this, GetRecomActivity::class.java)
           startActivity(intent)
       }

 }
}