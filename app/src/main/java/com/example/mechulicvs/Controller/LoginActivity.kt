package com.example.mechulicvs.Controller

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mechulicvs.MainMenuActivity
import com.example.mechulicvs.Model.UserDataAPI
import com.example.mechulicvs.R
import com.example.mechulicvs.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    //LoginActivity는 Controller & View 역할을 모두 한다.

    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val Id : String = binding.inputIdEt.text.toString()
        val Pw : String = binding.inputPwEt.text.toString()

        binding.loginBtn.setOnClickListener {
            val editor = getSharedPreferences("userInfo",MODE_PRIVATE).edit()
            editor.putString("userId", Id)
            editor.apply()
            //login 데이터 보내기
            sendUserLoginData(Id, Pw)
            
        }

    }

    private fun sendUserLoginData(id : String, pw : String){


        lifecycleScope.launch{
            val res = withContext(Dispatchers.IO){
                UserDataAPI.service.userLogin(id, pw)
            }
            val answer = res.isSuccess
            val userId = res.result.user_id
            if(answer){
                val intent = Intent(this@LoginActivity, MainMenuActivity::class.java)
                Toast.makeText(this@LoginActivity, "로그인이 완료되었습니다.", Toast.LENGTH_LONG).show()
//                intent.putExtra("id", userid)
                val editor = getSharedPreferences("userInfo",MODE_PRIVATE).edit()
                editor.putString("userId", userId)
                editor.apply()
                startActivity(intent)

            } else{
                Toast.makeText(this@LoginActivity, "아이디 혹은 비밀번호가 잘못되었습니다.", Toast.LENGTH_LONG).show()
            }
        }

    }

}