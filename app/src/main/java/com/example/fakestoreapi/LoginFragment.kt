package com.example.fakestoreapi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fakestoreapi.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

/**
 * Fragment for handling user login.
 *
 * Uses MVVM architecture, Hilt for DI, and ViewBinding for UI interaction.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    // Get ViewModel instance scoped to this Fragment
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout with ViewBinding
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        setupListeners()
        observeLoginResult()

        return binding.root
    }

    /**
     * Sets up click listeners for UI components.
     */
    private fun setupListeners() {
        binding.apply {
            // Navigate to Register screen
            textView6.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            // Handle sign in button click
            signInBtn.setOnClickListener {
                val email = emailLg.text.toString().trim()
                val password = passwordLogin.text.toString().trim()

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.login(email, password)
                } else {
                    Toast.makeText(requireContext(), "Enter a valid email and password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * Observes login LiveData from ViewModel and handles UI updates.
     */
    private fun observeLoginResult() {
        viewModel.loginResult.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val token = response.body()?.accessToken
                // Optionally: save token in SharedPreferences or DataStore

                // Navigate to Dashboard
                startActivity(Intent(requireContext(), DashboardActivity::class.java))
                requireActivity().finish()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Login failed. Please check your credentials.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}