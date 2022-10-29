package com.example.mechulicvs.Controller

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.mechulicvs.Model.IdDataAPI
import com.example.mechulicvs.Model.UserData
import com.example.mechulicvs.R
import com.example.mechulicvs.databinding.ActivitySignUpBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignUpBinding
    var Idcheck : Boolean = false
    var nextBtnCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var userId = binding.inputidEt.text
        var userPw = binding.inputpwEt.text
        var checkingPw = binding.inputpw2Et.text
        var nickName = binding.inputnicknameEt.text

        binding.duplicateCheckTv.setOnClickListener {
            val id = userId.toString()
            checkIdDuplicate(id)
        }

        binding.backpressBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.nextBtn.setOnClickListener {

            val id = userId.toString()
            val pw = userPw.toString()
            val checkpw = checkingPw.toString()
            val nick = nickName.toString()

            if(pw != checkpw){
                Toast.makeText(applicationContext, "두 비밀번호가 같지 않습니다.", Toast.LENGTH_LONG).show()
            } else{
                    sendUserData(id, pw, nick)
            }
        }

    }

    private fun sendUserData(id : String, pw : String, nickname : String){
        val intent = Intent(this, SignUpRatingActivity::class.java)
        intent.putExtra("userInfo", UserData(id, pw, nickname))
        startActivity(intent)
    }

    private fun checkIdDuplicate(userid : String){
        lifecycleScope.launch{
            val id = userid
            val res = withContext(Dispatchers.IO){
                IdDataAPI.userId = id
                IdDataAPI.service.isDuplicated()
            }
            val answer = res.isSuccess
            if(answer){
//                val intent = Intent(this@SignUpActivity, SignUpRatingActivity::class.java)
                Toast.makeText(this@SignUpActivity, "아이디 사용 가능합니다.", Toast.LENGTH_LONG).show()
                binding.DuplicateTv.visibility = View.VISIBLE
                Idcheck = true
            }
            else{
                binding.DuplicateTv.text = "이미 사용 중인 아이디입니다."
                binding.DuplicateTv.visibility = View.VISIBLE
                Idcheck = false
            }
        }
    }


}