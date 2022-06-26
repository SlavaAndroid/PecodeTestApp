package com.android.example.pecodetestapp.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.android.example.pecodetestapp.App


class Prefs() {

    private val preferences: SharedPreferences =
        App.appContext.getSharedPreferences(NAME_SP, Context.MODE_PRIVATE)

    fun saveCount(count: Int) {
        setInt(count, COUNT_KEY)
    }

    fun getCount(): Int {
        return getInt(COUNT_KEY)
    }

    private fun getInt(key: String, default: Int = 0): Int {
        return preferences.getInt(key, default)
    }

    private fun setInt(value: Int, key: String) {
        preferences.edit().putInt(key, value).apply()
    }

    companion object {
        private const val NAME_SP = "AppShared"
        private const val COUNT_KEY = "fragments_count"
    }

}