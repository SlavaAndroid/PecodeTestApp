package com.android.example.pecodetestapp.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.example.pecodetestapp.databinding.FragmentBaseBinding
import com.android.example.pecodetestapp.util.provider.NotificationProvider
import java.lang.IllegalStateException

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentBaseBinding
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments?.getInt(ARG) ?: throw IllegalStateException("Index must be present")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            btnCreateNotification.setOnClickListener {
                NotificationProvider.createNotification(position, requireContext())
            }
        }
    }

    companion object {
        private const val ARG = "index"
        fun newInstance(position: Int) = NotificationFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG, position)
            }
        }
    }
}