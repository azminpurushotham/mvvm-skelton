package com.azmin.skelton.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import dagger.Module

@Module
class SharedPreferenceUtils(val context: Context) {
    var preferences: SharedPreferences
    var editor: SharedPreferences.Editor

    init {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        editor = preferences.edit()
    }

    fun clearUser() : Boolean {
        editor.remove("user_detail")
       return editor.commit()
    }

    fun putUser(loginUserResponseModel: Any) {
        val json = Gson().toJson(loginUserResponseModel).toString()
        editor.putString("user_detail", json)
        editor.commit()
    }

    fun getUser(): Any? {
        val jSon = preferences.getString("user_detail", "")
        if (!jSon.isNullOrBlank()) {
//            return Gson().fromJson(jSon, LoginUserResponseModel::class.java)
        }
        return null
    }

    fun getToken(): String? {
        val jSon = preferences.getString("token", "")
        if (!jSon.isNullOrBlank()) {
//            return Gson().fromJson(jSon, LoginUserResponseModel::class.java)
        }
        return null
    }

    /*firebase token started*/
    fun clearFireBaseToken() :Boolean {
        editor.remove("FireBaseToken")
        return  editor.commit()
    }

    fun putFireBaseToken(token: String) {
        editor.putString("FireBaseToken", token)
        editor.commit()
    }

    fun getFireBaseToken(): String? {
        return preferences.getString("FireBaseToken", null)
    }

    fun getLanguage(): String {
        return "en"
    }


}