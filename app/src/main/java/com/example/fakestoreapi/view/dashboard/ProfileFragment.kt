package com.example.fakestoreapi.view.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import com.example.fakestoreapi.view.viewModel.ProfileViewModel
import com.example.fakestoreapi.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    // Declares a private variable for View Binding, allowing safe access to layout elements.
    private lateinit var binding: FragmentProfileBinding
    // Initializes the ProfileViewModel using Hilt's byViewModels() delegate.
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflates the layout for this fragment using View Binding.
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Observes the 'profileResponse' LiveData from the ViewModel.
        // This LiveData likely holds a NetworkResponse or similar wrapper
        // containing the API response for the user's profile.
        viewModel.profileResponse.observe (viewLifecycleOwner){ response ->

            // Checks if the network response was not successful.
            // If it was not successful, the observation is halted for this update.
            if (!response.isSuccessful) return@observe

            // Uses 'binding.apply' for concise access to UI elements within the binding object.
            binding.apply {

                // Safely accesses the body of the successful response.
                // If the body is not null, the 'profile' object is used.
                response.body()?.let { profile ->

                    // Sets the text of TextViews with data from the 'profile' object.
                    userName.text = profile.name
                    userEmail.text = profile.email
                    userRole.text = profile.role

                    // Loads the user's avatar image into 'imageView4' using a hypothetical 'load' extension function.
                    // This suggests the use of an image loading library like Coil or Glide.
                    imageView4.load(profile.avatar.toString())
                }
            }
        }
        // Returns the root view of the inflated layout.
        return binding.root
    }
}