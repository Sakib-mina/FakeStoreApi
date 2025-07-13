package com.example.fakestoreapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fakestoreapi.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment() {

    // View Binding instance for this fragment's layout
    private lateinit var binding: FragmentRegisterBinding

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
        return binding.root
    }

    // You can add lifecycle methods here for UI setup, listeners, etc.
}