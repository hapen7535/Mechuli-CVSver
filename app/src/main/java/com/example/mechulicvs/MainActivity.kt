package com.example.mechulicvs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechulicvs.Controller.LoginActivity
import com.example.mechulicvs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.signInBtn.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java) //login의 View로 이동
            startActivity(intent)

        }

        binding.signUpBtn.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        }

    }
}