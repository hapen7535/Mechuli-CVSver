package com.example.mechulicvs.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mechulicvs.Model.IdDataAPI
import com.example.mechulicvs.Model.UserData
import com.example.mechulicvs.R
import com.example.mechulicvs.SignUpRatingActivity
import com.example.mechulicvs.databinding.ActivitySignUpBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignUpBinding
    var Idcheck : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding.nextBtn.isEnabled = false

        binding = ActivitySignUpBinding.inflate(layoutInflater)

        val userId = binding.inputidEt.text.toString()
        val userPw = binding.inputpw2Et.text.toString()
        val nickName = binding.inputnicknameEt.text.toString()

        binding.duplicateCheckTv.setOnClickListener {
            checkIdDuplicate(userId)
        }

        if(Idcheck){
            binding.nextBtn.isEnabled = true
            binding.nextBtn.setBackgroundResource(R.drawable.subbtn_enable_check)
        }

        binding.nextBtn.setOnClickListener {
            sendUserData(userId, userPw, nickName)
        }



    }

    private fun sendUserData(id : String, pw : String, nickname : String){
        val intent = Intent(this, SignUpRatingActivity::class.java)
        intent.putExtra("userInfo", UserData(id, pw, nickname))
        startActivity(intent)
    }

    private fun checkIdDuplicate(id : String){
        lifecycleScope.launch{
            val res = withContext(Dispatchers.IO){
                IdDataAPI.userId = id
                IdDataAPI.service.isDuplicated()
            }
            val answer = res.isSuccess
            if(answer){
                val intent = Intent(this@SignUpActivity, SignUpRatingActivity::class.java)
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