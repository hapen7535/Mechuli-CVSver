package com.example.mechulicvs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechulicvs.databinding.ActivityGetRecomBinding

class GetRecomActivity : AppCompatActivity() {

    lateinit var binding : ActivityGetRecomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_recom)

        binding = ActivityGetRecomBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}