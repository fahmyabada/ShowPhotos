package com.mohamedfahmy.taskkoinz.data.api

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SessionManager @Inject constructor(@ApplicationContext private val context: Context) {

    // Shared Preferences
    private val pref: SharedPreferences? = context.getSharedPreferences("fahmyabada@gmail.com", 0)

    // Editor for Shared preferences
    private val editor: SharedPreferences.Editor? = pref?.edit()

    fun putBoolean(key: String, value: Boolean) {
        editor?.putBoolean(key, value)?.apply()
    }

    fun getBoolean(key: String): Boolean? {
        return pref?.getBoolean(key, false)
    }

    fun putString(key: String, value: String) {
        editor?.putString(key, value)?.apply()
    }

    fun getString(key: String): String? {
        return pref?.getString(key, null)
    }

    fun putInt(key: String, value: Int) {
        editor?.putInt(key, value)?.apply()
    }

    fun getInt(key: String): Int? {
        return pref?.getInt(key, 0)
    }

}