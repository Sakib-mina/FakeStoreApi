package com.example.fakestoreapi.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fakestoreapi.view.viewModel.AuthViewModel
import com.example.fakestoreapi.view.activity.DashboardActivity
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

/**
 * Fragment responsible for handling user authentication (login) functionality.
 *
 * Implements:
 * - MVVM architecture pattern
 * - Hilt for dependency injection
 * - ViewBinding for safe view access
 * - LiveData for observing authentication state
 * - Navigation Component for screen transitions
 *
 * Flow:
 * 1. User enters credentials
 * 2. Validates input client-side
 * 3. Sends request via ViewModel
 * 4. Observes response and navigates to Dashboard on success
 */
@AndroidEntryPoint  // Enables Hilt dependency injection for this Fragment
class LoginFragment : Fragment() {

    // ViewBinding instance for safely accessing views
    private lateinit var binding: FragmentLoginBinding

    /**
     * AuthViewModel instance scoped to this Fragment's lifecycle.
     * Handles all authentication business logic.
     */
    private val viewModel: AuthViewModel by viewModels()  // Uses property delegation for lazy initialization

    /**
     * Called to create the fragment's view hierarchy.
     *
     * @param inflater LayoutInflater to inflate views
     * @param container Parent view group for attachment
     * @param savedInstanceState Previously saved state (if any)
     * @return Root View of the inflated layout
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize ViewBinding
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Set up UI interaction handlers
        setupListeners()

        // Configure LiveData observers
        observeLoginResult()

        return binding.root
    }

    /**
     * Configures all click listeners and user interaction handlers.
     *
     * Handles:
     * - Navigation to registration screen
     * - Login button submission
     * - Input validation
     */
    private fun setupListeners() {
        binding.apply {
            // Registration navigation text click
            textView6.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            // Login button click handler
            signInBtn.setOnClickListener {
                val email = emailLg.text.toString().trim()
                val password = passwordLogin.text.toString().trim()

                when {
                    // Validate input fields
                    email.isEmpty() || password.isEmpty() -> {
                        showToast("Enter a valid email and password")
                    }
                    else -> {
                        // Initiate login process via ViewModel
                        viewModel.login(email, password)
                    }
                }
            }
        }
    }

    /**
     * Observes authentication state changes from ViewModel.
     *
     * Handles three scenarios:
     * 1. Successful login (navigates to Dashboard)
     * 2. Failed login (shows error)
     * 3. Network/other errors (handled by ViewModel)
     */
    private fun observeLoginResult() {
        viewModel.loginResult.observe(viewLifecycleOwner) { response ->
            when {
                response.isSuccessful -> {
                    val token = response.body()?.accessToken
                    // Note: Token should be persisted securely (SharedPrefs/DataStore)

                    // Transition to main application flow
                    startActivity(Intent(requireContext(), DashboardActivity::class.java))
                    requireActivity().finish()  // Prevent back navigation to login
                }
                else -> {
                    showToast("Login failed. Please check your credentials.")
                }
            }
        }
    }

    /**
     * Helper method to display Toast messages.
     *
     * @param message The text to display in the Toast
     */
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}