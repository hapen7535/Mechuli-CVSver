package com.example.mechulicvs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainMenuActivity : AppCompatActivity() {

    lateinit var userId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        userId = "id"

    }
}