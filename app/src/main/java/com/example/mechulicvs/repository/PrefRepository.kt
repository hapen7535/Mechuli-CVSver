package com.example.mechulicvs.repository

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


class PrefRepository(context : Context){

    private val prefs : SharedPreferences =
        context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

    fun getString(key : String, defValue : String) : String {
        return prefs.getString(key, defValue).toString()
    }

    fun getInt(key : String, defValue: Int) : Int {
        return prefs.getInt(key, defValue)
    }

    fun getFloat(key : String, defValue : Float) : Float {
        return prefs.getFloat(key, defValue)
    }

    fun setString(key : String, str : String){
        prefs.edit().putString(key, str).apply()
    }

    fun setInt(key : String, num : Int){
        prefs.edit().putInt(key, num).apply()
    }

    fun setFloat(key : String, rating: Float){
        prefs.edit().putFloat(key, rating).apply()
    }

    private val gson = Gson()

}