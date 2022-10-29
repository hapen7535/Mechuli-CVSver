package com.example.mechulicvs.Controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.example.mechulicvs.Model.MenuList
import com.example.mechulicvs.Model.UserData
import com.example.mechulicvs.Model.UserDataAPI.signUpService
import com.example.mechulicvs.Model.UserDataAPI.signupMenuDataService
import com.example.mechulicvs.R
import com.example.mechulicvs.SignUpViewModel
import com.example.mechulicvs.databinding.ActivitySignUpRatingBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpRatingActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignUpRatingBinding
    lateinit var userid : String; lateinit var userpw : String; lateinit var nickname : String
    lateinit var signUpViewModel: SignUpViewModel

    var ratingList = mutableMapOf<Int, Double>()
    lateinit var menuId : MutableList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_rating)

        binding = ActivitySignUpRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backpressBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val signInfo = intent.getSerializableExtra("userInfo") as UserData
//        userid = signInfo.userId;userpw = signInfo.userPw;nickname = signInfo.nickName

        var imgList = arrayListOf(binding.ratingSampleIv, binding.ratingSampleIv2, binding.ratingSampleIv3, binding.ratingSampleIv4, binding.ratingSampleIv5)
        var itemNameList = arrayListOf(binding.itemNameTv, binding.itemNameTv2, binding.itemNameTv3, binding.itemNameTv4, binding.itemNameTv5)
        var storeNameList = arrayListOf(binding.companyNameTv,binding.companyNameTv2, binding.companyNameTv3, binding.companyNameTv4, binding.companyNameTv5)
        var ratingBars = arrayListOf(binding.itemRatingRv, binding.itemRatingRv2, binding.itemRatingRv3, binding.itemRatingRv4, binding.itemRatingRv5)

        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

//        getMenuImg(itemNameList, imgList, storeNameList)
//        filltheRatings(ratingBars, ratingList)

        var i = 0

        val observer = Observer<List<MenuList>> { list ->
            list.forEach {
                itemNameList[i].setText(it.menu_name)
                storeNameList[i].setText(it.store_name)
                ratingBars[i].setOnRatingBarChangeListener(OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                   Log.d("rating", "ratingChanged" + rating.toString())
                    ratingList[it.menu_id] = ratingBar.rating.toDouble()
                    Log.d("ratingList", ratingList.toString())
                })
                imgList[i].load(it.menu_image){
                    transformations(CircleCropTransformation())
                }
                Log.d("list", itemNameList.toString() + storeNameList.toString() + ratingList.toString())
                i += 1
            }

        }

        signUpViewModel.getResultRepository()!!.observe(this, observer)

        binding.nextBtn.setOnClickListener {
            Log.d("rating Size", "${ratingList.size}")
            if(ratingList.size < 5){
                Toast.makeText(this, "해당 메뉴의 점수를 모두 매겨주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                sendUserData(signInfo, ratingList)
            }
        }

    }

//
//    private fun getMenuImg(itemnamelist : ArrayList<TextView>, imglist : ArrayList<ImageView>, storenamelist : ArrayList<TextView>){
//
//        var i = 0
//        menuId = mutableListOf()
//
//        lifecycleScope.launch {
//            Log.d("myTag", "서버 요청 실행")
//            val res = withContext(Dispatchers.IO) {
//                signupMenuDataService.getSignupMenuData()
//            }
//            val answer = res
//            if(answer.isSuccess){
//                answer.result.forEach {
//                    itemnamelist[i].setText(it.menu_name)
//                    storenamelist[i].setText(it.store_name)
//                    Log.d("store_name", storenamelist.toString())
//                    imglist[i].load(it.menu_image)
//                    {
//                        transformations(CircleCropTransformation())
//                    }
//                    menuId.add(it.menu_id)
//                    i += 1
//                }
//            }
//
//        }
//
//    }

    private fun sendUserData(userData: UserData,ratings : MutableMap<Int, Double>){
        lifecycleScope.launch{
            val res = withContext(Dispatchers.IO){
                signUpService.userSingup(userData.userId,userData.userPw,userData.nickName,ratings)
            }
            if(res.isSuccess){
                val intent = Intent(this@SignUpRatingActivity, LoginActivity::class.java)
                Toast.makeText(this@SignUpRatingActivity, "회원가입 처리가 정상적으로 완료되었습니다.", Toast.LENGTH_LONG).show()
                startActivity(intent)
            } else{
                Toast.makeText(this@SignUpRatingActivity, "회원가입이 비정상적으로 처리되었습니다. 다시 회원가입을 진행해주세요", Toast.LENGTH_LONG).show()
            }
        }
    }

}