package com.android.example.pecodetestapp.data.repository

interface DataRepository {
    fun getFragmentCount(): Int
    fun setFragmentCount(count: Int)
}