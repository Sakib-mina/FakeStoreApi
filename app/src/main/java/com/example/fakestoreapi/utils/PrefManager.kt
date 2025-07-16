package com.example.fakestoreapi.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefManager @Inject constructor(@ApplicationContext context: Context ){

    private val pref = context.getSharedPreferences("app=prefs", Context.MODE_PRIVATE)

    fun setPref(key : String, value:String){

        val editor = pref.edit()

        editor.putString(key, value)
        editor.apply()


    }
    fun getPref(key: String): String{
        val prefValue = pref.getString(key, "")
        return prefValue.toString()
    }

    fun isLoggedIn(): Boolean =
        getPref(Keys.ACCESS_TOKEN).isNotBlank()

    fun clear() {
        pref.edit().clear().apply()
    }
}