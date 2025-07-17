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


/**
 * Fragment responsible for user registration (signup).
 *
 * Uses View Binding to access views and an AuthViewModel for
 * handling registration logic with LiveData observation.
 *
 * Annotated with @AndroidEntryPoint for Hilt dependency injection.
 */
@AndroidEntryPoint
class RegisterFragment : Fragment() {

    // View Binding instance to access views from fragment_register.xml
    private lateinit var binding: FragmentRegisterBinding

    // ViewModel instance scoped to this Fragment, provided by Hilt
    private val viewModel: AuthViewModel by viewModels()

    /**
     * Inflates the fragment's layout using View Binding and initializes
     * user creation and observer setup.
     *
     * @param inflater The LayoutInflater object to inflate views
     * @param container The parent ViewGroup (if any) to attach the fragment's UI
     * @param savedInstanceState Previously saved state (if any)
     * @return The root view of the inflated layout
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout using View Binding
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        setupUserCreation()
        observeRegisterResult()

        return binding.root
    }

    /**
     * Observes LiveData from the ViewModel for registration response.
     * On success, navigates to DashboardActivity and finishes current activity.
     * On failure, shows a Toast message.
     */
    private fun observeRegisterResult() {
        viewModel.registerResult.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                // Registration successful: navigate to dashboard
                val user = response.body()
                startActivity(Intent(requireContext(), DashboardActivity::class.java))
                requireActivity().finish()
            } else {
                // Registration failed: notify user
                Toast.makeText(context, "Signup failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Sets up click listener on the signup button.
     * Collects user input and triggers the registration process
     * via the ViewModel.
     */
    private fun setupUserCreation() {
        binding.signUpBtn.setOnClickListener {
            val name = binding.fullName.text.toString().trim()
            val email = binding.signUpEmail.text.toString().trim()
            val password = binding.signUpPassword.text.toString().trim()
            val avatar = "https://api.lorem.space/image/face?w=640&h=480"

            // TODO: Add input validation before calling register

            viewModel.register(name = name, email = email, password = password, avatar = avatar)
        }
    }

    // Additional lifecycle methods can be added here for UI setup or event handling
}
