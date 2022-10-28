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

        val userId = binding.inputidEt.text.toString()
        val userPw = binding.inputpwEt.text.toString()
        val checkingPw = binding.inputpw2Et.text.toString()
        val nickName = binding.inputnicknameEt.text.toString()

        binding.duplicateCheckTv.setOnClickListener {
            checkIdDuplicate(userId)
        }

        binding.backpressBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

//        fun enableSubmitIfReady(editText: EditText) {
//            val isReady: Boolean = editText.getText().toString().length > 2
//            if(isReady) nextBtnCount += 1
//            Log.d("count", nextBtnCount.toString())
//        }
//
//        binding.inputidEt.addTextChangedListener(object : TextWatcher {
//
//            override fun afterTextChanged(p0: Editable?) {
//                enableSubmitIfReady(binding.inputidEt)
//            }
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//        })
//
//        binding.inputpwEt.addTextChangedListener(object : TextWatcher {
//
//            override fun afterTextChanged(p0: Editable?) {
//                enableSubmitIfReady(binding.inputpwEt)
//            }
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//        })
//
//        binding.inputpw2Et.addTextChangedListener(object : TextWatcher {
//
//            override fun afterTextChanged(p0: Editable?) {
//                enableSubmitIfReady(binding.inputpw2Et)
//            }
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//        })
//
//        binding.inputnicknameEt.addTextChangedListener(object : TextWatcher {
//
//            override fun afterTextChanged(p0: Editable?) {
//                enableSubmitIfReady(binding.inputnicknameEt)
//                binding.nextBtn.isEnabled = true
//                binding.nextBtn.setBackgroundColor(R.drawable.subbtn_enable_check)
//            }
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//        })

//        if(nextBtnCount > 4){
//            Log.d("btn","버튼 가능 ")
//            binding.nextBtn.isEnabled = true
//            binding.nextBtn.setBackgroundResource(R.drawable.subbtn_enable_check)
//        }

        binding.nextBtn.setOnClickListener {
            if(userPw != checkingPw){
                Toast.makeText(applicationContext, "두 비밀번호가 같지 않습니다.", Toast.LENGTH_LONG).show()
            } else{
                    sendUserData(userId, userPw, nickName)
            }
        }

    }

//    override fun onResume() {
//        super.onResume()
//        if(nextBtnCount < 4){
//            Log.d("btn","버튼 불가능 ")
//            binding.nextBtn.isEnabled = false
//            binding.nextBtn.setBackgroundResource(R.drawable.input_short_background_shape)
//        } else{
//            Log.d("btn","버튼 가능 ")
//            binding.nextBtn.isEnabled = true
//            binding.nextBtn.setBackgroundColor(R.drawable.subbtn_enable_check)
//        }
//    }

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