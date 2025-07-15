package com.example.fakestoreapi.view.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import com.example.fakestoreapi.ProfileViewModel
import com.example.fakestoreapi.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        viewModel.profileResponse.observe (viewLifecycleOwner){

            if (!it.isSuccessful) return@observe

            binding.apply {

                it.body()?.let { profile->

                    userName.text = profile.name
                    userEmail.text = profile.email
                    userRole.text = profile.role

                    imageView4.load(profile.avatar.toString())
                }
            }
        }
        return binding.root
    }
}