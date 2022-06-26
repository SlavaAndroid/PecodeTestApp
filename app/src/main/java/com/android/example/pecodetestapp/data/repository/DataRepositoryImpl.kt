package com.android.example.pecodetestapp.data.repository

import com.android.example.pecodetestapp.data.prefs.Prefs

class DataRepositoryImpl : DataRepository {

    private val prefs = Prefs()

    override fun getFragmentCount(): Int {
        return prefs.getCount()
    }

    override fun setFragmentCount(count: Int) {
        prefs.saveCount(count)
    }

}