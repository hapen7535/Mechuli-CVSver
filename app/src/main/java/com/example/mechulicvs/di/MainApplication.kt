package com.example.mechulicvs.di

import android.app.Application
import com.example.mechulicvs.repository.PrefRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    companion object{
        lateinit var prefs : PrefRepository
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PrefRepository(applicationContext)
    }

}