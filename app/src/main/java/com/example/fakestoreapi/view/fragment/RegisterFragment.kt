package com.example.fakestoreapi.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.fakestoreapi.databinding.FragmentRegisterBinding
import com.example.fakestoreapi.view.activity.DashboardActivity
import com.example.fakestoreapi.view.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment() {

    // View Binding instance for this fragment's layout
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: AuthViewModel by viewModels()

    /**
     * Called to have the fragment instantiate its user interface view.
     * Here we inflate the layout using ViewBinding and return the root view.
     *
     * @param inflater LayoutInflater to inflate views in the fragment
     * @param container Optional parent view that the fragment's UI should be attached to
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state
     * @return The root view of the fragment's layout
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize view binding for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        createUser()
        userObserver()

        return binding.root
    }

    private fun userObserver() {
        viewModel.registerResult.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val user = response.body()
                startActivity(Intent(requireContext(), DashboardActivity::class.java))
                requireActivity().finish()
            } else {
                Toast.makeText(context, "Signup failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createUser() {
        binding.signUpBtn.setOnClickListener {
            val name = binding.fullName.text.toString()
            val email = binding.signUpEmail.text.toString()
            val password = binding.signUpPassword.text.toString()
            val avatar = "https://api.lorem.space/image/face?w=640&h=480"

           viewModel.register(name = name,email = email, password = password, avatar = avatar)
        }
    }
    // You can add lifecycle methods here for UI setup, listeners, etc.
}