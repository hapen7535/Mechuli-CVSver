package com.example.mechulicvs.View

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mechulicvs.R
import com.example.mechulicvs.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)

    }

    fun onClickLoginBtn(view : View){
        //login 데이터 보내기
        //response에 따라 화면 전환

    }

}