package com.example.fakestoreapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fakestoreapi.databinding.FragmentStartBinding


class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentStartBinding.inflate(inflater, container, false)

        binding.apply {
            loginBtn.setOnClickListener { findNavController().navigate(R.id.action_startFragment_to_loginFragment) }
            registerBtn.setOnClickListener { findNavController().navigate(R.id.action_startFragment_to_registerFragment) }
        }

        return binding.root
    }
}