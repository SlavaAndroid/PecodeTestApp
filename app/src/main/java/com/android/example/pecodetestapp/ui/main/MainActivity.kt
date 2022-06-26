package com.android.example.pecodetestapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.android.example.pecodetestapp.databinding.ActivityMainBinding
import com.android.example.pecodetestapp.ui.main.adapter.SlidePagerAdapter
import com.android.example.pecodetestapp.util.provider.NOTIFICATION_NUMBER_KEY
import com.android.example.pecodetestapp.util.provider.NotificationProvider
import com.android.example.pecodetestapp.util.setVisibility

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var pagerAdapter: SlidePagerAdapter
    private val onPageListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            setCurrentPageText(position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        initViews()
        initViewModel()
        checkIntent(intent)
    }

    private fun checkIntent(intent: Intent?) {
        intent?.let {
            val position = it.getIntExtra(NOTIFICATION_NUMBER_KEY, -1)
            if (position != -1) {
                viewModel.setPage(position)
            }
        }
    }

    private fun initViews() {
        with(binding) {
            pagerAdapter = SlidePagerAdapter(this@MainActivity)
            viewPager.adapter = pagerAdapter
            viewPager.registerOnPageChangeCallback(onPageListener)
            btnAdd.setOnClickListener {
                viewModel.addScreen()
            }
            btnDelete.setOnClickListener {
                viewModel.deleteScreen()
                val position = pagerAdapter.itemCount - 1
                NotificationProvider.removeNotification(position, this@MainActivity)
            }
        }
    }

    private fun initViewModel() {
        viewModel.pageCount.observe(this, {
            binding.btnDelete.setVisibility(it > 1)
            configAdapter(it)
        })

        viewModel.selectPage.observe(this, {
            if (it != -1)
                binding.viewPager.currentItem = it
        })
    }

    private fun setCurrentPageText(position: Int) {
        binding.tvPageNumber.text = position.inc().toString()
    }

    private fun configAdapter(count: Int) {
        val value = if (count == 0) 1 else count
        pagerAdapter.itemCount = value
        pagerAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        binding.viewPager.unregisterOnPageChangeCallback(onPageListener)
        super.onDestroy()
    }
}