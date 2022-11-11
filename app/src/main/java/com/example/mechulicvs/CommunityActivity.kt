package com.example.mechulicvs

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.mechulicvs.databinding.ActivityCommunityBinding


class CommunityActivity : AppCompatActivity() {

    lateinit var binding : ActivityCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        binding = ActivityCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(
            R.id.community_fragment_frame,
            MainCommunityFragment()
        )
        transaction.commit()


    }
}