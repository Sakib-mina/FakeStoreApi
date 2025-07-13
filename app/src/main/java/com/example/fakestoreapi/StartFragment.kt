package com.example.fakestoreapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fakestoreapi.databinding.FragmentStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment() {

    // ViewBinding instance for this fragment
    private lateinit var binding: FragmentStartBinding

    /**
     * Inflate the layout and setup click listeners for buttons.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state
     * @return The root view of the inflated layout
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using ViewBinding
        binding = FragmentStartBinding.inflate(inflater, container, false)

        // Setup button click listeners for navigation
        binding.apply {
            loginBtn.setOnClickListener {
                // Navigate to LoginFragment
                findNavController().navigate(R.id.action_startFragment_to_loginFragment)
            }
            registerBtn.setOnClickListener {
                // Navigate to RegisterFragment
                findNavController().navigate(R.id.action_startFragment_to_registerFragment)
            }
        }

        return binding.root
    }
}