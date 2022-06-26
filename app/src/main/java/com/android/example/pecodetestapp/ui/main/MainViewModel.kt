package com.android.example.pecodetestapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.example.pecodetestapp.data.repository.DataRepositoryImpl

class MainViewModel : ViewModel() {
    private val repository = DataRepositoryImpl()

    var pageCount = MutableLiveData(repository.getFragmentCount())
    var selectPage = MutableLiveData(-1)

    fun addScreen() {
        val newValue = repository.getFragmentCount().inc()
        updateCount(newValue)
    }

    fun deleteScreen() {
        val newValue = repository.getFragmentCount().dec()
        if (newValue > 0) {
            updateCount(newValue)
        }
    }

    fun setPage(position: Int) {
        selectPage.postValue(position)
    }

    private fun updateCount(newValue: Int) {
        repository.setFragmentCount(newValue)
        pageCount.postValue(newValue)
    }
}