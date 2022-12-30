package com.example.mechulicvs.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mechulicvs.R
import com.example.mechulicvs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var SignInBtn : Button
    lateinit var SignUpBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SignInBtn = findViewById<Button>(R.id.signIn_btn)
        SignUpBtn = findViewById<Button>(R.id.signUp_btn)

        SignInBtn.setOnClickListener {

//            Log.d("setonclicklistner","클릭됨")
            val intent = Intent(this, LoginActivity::class.java) //login의 View로 이동
            startActivity(intent)

        }

        SignUpBtn.setOnClickListener { view ->
//            Log.d("singupbtn","클릭됨")
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        }

    }
}