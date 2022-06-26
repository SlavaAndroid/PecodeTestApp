package com.android.example.pecodetestapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.example.pecodetestapp.databinding.FragmentBaseBinding

//class ViewPagerAdapter: RecyclerView.Adapter<PagerViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
//        val itemBinding =
//            FragmentBaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return PagerViewHolder(itemBinding)
//    }
//
//    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
//        holder.bind()
//    }
//
//    override fun getItemCount(): Int {
//        return itemCount
//    }
//}
//
//class PagerViewHolder(binding: FragmentBaseBinding): RecyclerView.ViewHolder(binding.root) {
//    private val item = binding
//
//    fun bind() {
//        with(item) {
//
//        }
//    }
//}