package com.example.mechulicvs.Repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


class PrefRepository(context : Context){

    private val prefs : SharedPreferences =
        context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

    fun getString(key : String, defValue : String) : String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key : String, str : String){
        prefs.edit().putString(key, str).apply()
    }

    private val gson = Gson()

}