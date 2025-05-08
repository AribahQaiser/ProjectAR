package com.example.myapplication.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.ItemsDetailActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }
    // Init Listener
    private fun initListener(){
        binding.bedCategoryLayout.setOnClickListener {
            // Handle bed category click
            val intent = Intent(requireContext(), ItemsDetailActivity::class.java)
            intent.putExtra("is_sofa", false) // or false depending on condition
            startActivity(intent)
        }
        binding.sofaCategoryLayout.setOnClickListener {
            // Handle sofa category click
            val intent = Intent(requireContext(), ItemsDetailActivity::class.java)
            intent.putExtra("is_sofa", true) // or false depending on condition
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}