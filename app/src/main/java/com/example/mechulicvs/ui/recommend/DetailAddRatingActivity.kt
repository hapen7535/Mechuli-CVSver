package com.example.mechulicvs.ui.recommend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.example.mechulicvs.MainApplication
import com.example.mechulicvs.data.remote.model.Result
import com.example.mechulicvs.R
import com.example.mechulicvs.viewmodel.recommend.DetailAddRatingSetViewModel
import com.example.mechulicvs.viewmodel.recommend.DetailAddRatingViewModel
import com.example.mechulicvs.databinding.ActivityDetailAddRatingBinding

class DetailAddRatingActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailAddRatingBinding
    lateinit var viewModel: DetailAddRatingViewModel
    lateinit var setViewModel : DetailAddRatingSetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_add_rating)

        binding = ActivityDetailAddRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemId = intent.getIntExtra("itemId", 0)
        MainApplication.prefs.setInt("menuId", itemId)

        viewModel = ViewModelProvider(this).get(DetailAddRatingViewModel::class.java)

        val observer = Observer<Result> { it ->

            binding.ratingSampleIv.load(it.menuImg) {
                transformations(CircleCropTransformation())
            }

            binding.itemBrandNameTv.setText(it.cvsName)
            binding.itemNameTv.setText(it.menuName)
            binding.itemRatingbar.rating = it.score.toFloat()


        }

        binding.itemRatingbar.setOnRatingBarChangeListener{ _, rating, _ ->
            var changedRating = rating
            MainApplication.prefs.setFloat("rating", changedRating)
            setViewModel = ViewModelProvider(this).get(DetailAddRatingSetViewModel::class.java)
            val setObserver = Observer<Boolean>{
                if(it){
                    Toast.makeText(this, "평가 이력 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            setViewModel.getResultRepository().observe(this, setObserver)
        }


        viewModel.getResultRepository().observe(this, observer)

    }
}