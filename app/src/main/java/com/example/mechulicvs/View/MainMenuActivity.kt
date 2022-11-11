package com.example.mechulicvs.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechulicvs.R
import com.example.mechulicvs.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spf = getSharedPreferences("userInfo", MODE_PRIVATE)
        val userid = spf.getString("userId", "")!!

        binding.userIdTv.setText(userid)

        binding.mainmenuCommunityBtn.setOnClickListener {
            val intent = Intent(this, CommunityActivity::class.java)
            startActivity(intent)
        }

        binding.mainmenuRecommendBtn.setOnClickListener {
            val intent = Intent(this, RecommendActivity::class.java)
            startActivity(intent)
        }

    }
}