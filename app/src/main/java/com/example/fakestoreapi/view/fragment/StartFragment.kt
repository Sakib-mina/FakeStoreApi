package com.example.fakestoreapi.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentStartBinding
import com.example.fakestoreapi.view.activity.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * StartFragment serves as the entry point of the application, providing options for users
 * to either login or register. This fragment uses ViewBinding for view interaction and
 * Hilt for dependency injection.
 *
 * Features:
 * - Navigation to Login (currently redirects to DashboardActivity)
 * - Navigation to Registration
 * - Clean UI setup with ViewBinding
 */
@AndroidEntryPoint // Enables Hilt dependency injection for this fragment
class StartFragment : Fragment() {

    // ViewBinding instance for this fragment, initialized in onCreateView
    private lateinit var binding: FragmentStartBinding

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater The LayoutInflater object that inflates views in the fragment
     * @param container The parent view that the fragment's UI should be attached to
     * @param savedInstanceState Bundle containing previous state (if fragment is being recreated)
     * @return The root View for the fragment's UI
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize ViewBinding by inflating the layout
        binding = FragmentStartBinding.inflate(inflater, container, false)

        // Set up click listeners for the buttons
        setupButtonListeners()

        return binding.root
    }

    /**
     * Configures click listeners for the login and register buttons.
     *
     * Note: Currently the login button redirects directly to DashboardActivity
     * instead of navigating to LoginFragment (commented code shows original intent).
     */
    private fun setupButtonListeners() {
        binding.apply {
            loginBtn.setOnClickListener {
                // Original navigation to LoginFragment (currently commented out)
                // findNavController().navigate(R.id.action_startFragment_to_loginFragment)

                // Temporary implementation: Directly launch DashboardActivity
                startActivity(Intent(requireContext(), DashboardActivity::class.java))
                requireActivity().finish() // Close the current activity to prevent going back
            }

            registerBtn.setOnClickListener {
                // Navigate to RegisterFragment using Navigation Component
                findNavController().navigate(R.id.action_startFragment_to_registerFragment)
            }
        }
    }
}