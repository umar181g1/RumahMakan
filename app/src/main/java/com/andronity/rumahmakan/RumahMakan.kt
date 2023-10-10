package com.andronity.rumahmakan

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import com.andronity.rumahmakan.network.Api

class RumahMakan : MultiDexApplication() {

    companion object{
        lateinit var instance : RumahMakan

        fun getApp() : RumahMakan{
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getPrefrences() : SharedPreferences{
        return  PreferenceManager.getDefaultSharedPreferences(this)

    }

    fun setToken(token:String){
        getPrefrences().edit().putString("PREFERENCES_TOKEN", token).apply()
        Api.getInstance().buildRetrofitClient(token)
    }

    fun getToken():String?{
        return getPrefrences().getString("PREFERENCES_TOKEN",null)
    }

    fun setUser(user:String){
        getPrefrences().edit().putString("PREFERENCES_TOKEN", user).apply()
        Api.getInstance().buildRetrofitClient(user)
    }

    fun getUser():String?{
        return getPrefrences().getString("PREFERENCES_TOKEN",null)
    }
}